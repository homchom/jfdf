package net.jfdf.addon.splitter;

import net.jfdf.jfdf.IBlocksAddon;
import net.jfdf.jfdf.blocks.*;
import net.jfdf.jfdf.mangement.CodeManager;
import net.jfdf.jfdf.mangement.Control;
import net.jfdf.jfdf.values.Number;
import net.jfdf.jfdf.values.Tag;

import java.util.*;

public class CodeSplitter implements IBlocksAddon {
    public static CodeManager.PLOT_SIZE plotSize = CodeManager.PLOT_SIZE.BASIC;

    @Override
    public Map<CodeHeader, List<CodeBlock>> onPreGenerateLine(CodeHeader codeHeader, List<CodeBlock> blocks) {
        int maxLength = switch (plotSize) {
            case BASIC -> 48;
            case LARGE -> 98;
            case MASSIVE -> 298;
        };

        String codeHeaderName = "<Unknown>";

        if(codeHeader instanceof FunctionBlock) {
            codeHeaderName = ((FunctionBlock) codeHeader).getName();
        }

        int blocksTotalLength = 0;
        for(CodeBlock block : blocks) {
            if(block instanceof RepeatBlock
                    || block instanceof IfPlayerBlock
                    || block instanceof IfEntityBlock
                    || block instanceof IfGameBlock
                    || block instanceof IfVariableBlock) {
                blocksTotalLength++;
            } else if(block instanceof BracketBlock) {
                blocksTotalLength += ((BracketBlock) block).isClose ? 2 : 1;
            } else {
                blocksTotalLength += 2;
            }
        }

        if(blocksTotalLength > maxLength) {
            Scope lineScope;

            {
                List<List<CodeBlock>> scopeCodeBlocksStack = new ArrayList<>();
                List<Integer> scopeStartStack = new ArrayList<>();

                scopeCodeBlocksStack.add(new ArrayList<>());

                for (int i = 0; i < blocks.size(); i++) {
                    CodeBlock block = blocks.get(i);

                    if (block instanceof BracketBlock) {
                        if (((BracketBlock) block).isClose) {
                            int startBracketIndex = scopeStartStack.remove(scopeStartStack.size() - 1);
                            List<CodeBlock> scopeContent = scopeCodeBlocksStack.remove(scopeCodeBlocksStack.size() - 1);

                            scopeCodeBlocksStack
                                    .get(scopeCodeBlocksStack.size() - 1)
                                    .add(
                                            new IfScope(
                                                    blocks.get(startBracketIndex - 1),
                                                    blocks.get(startBracketIndex),
                                                    scopeContent,
                                                    block
                                            )
                                    );
                        } else {
                            List<CodeBlock> previousScopeBlocks = scopeCodeBlocksStack.get(scopeCodeBlocksStack.size() - 1);
                            previousScopeBlocks.remove(previousScopeBlocks.size() - 1);

                            scopeStartStack.add(i);
                            scopeCodeBlocksStack.add(new ArrayList<>());
                        }

                        continue;
                    }

                    scopeCodeBlocksStack.get(scopeCodeBlocksStack.size() - 1).add(block);
                }

                lineScope = new Scope(scopeCodeBlocksStack.get(0));
            }

            Map<List<CodeBlock>, Integer> splittedCode = new LinkedHashMap<>();

            blocks.clear();
            blocks.addAll(unwrapAndUpdateReturn(new Scope(splitScope(lineScope, codeHeaderName, splittedCode, maxLength, maxLength, 0)), 0));

            Map<CodeHeader, List<CodeBlock>> result = new LinkedHashMap<>();

            int i = 0;
            for (Map.Entry<List<CodeBlock>, Integer> entry : splittedCode.entrySet()) {
                result.put(
                        new FunctionBlock(
                                codeHeaderName + "_" + (i + 2)
                        ).SetTags(
                                new Tag(
                                        "Is Hidden",
                                        "True"
                                )
                        ), unwrapAndUpdateReturn(new Scope(entry.getKey()), entry.getValue())
                );

                i++;
            }

            return result;
        }

        return null;
    }

    private static List<CodeBlock> splitScope(Scope scope, String codeHeaderName, Map<List<CodeBlock>, Integer> splittedCode, int maxLength, int functionMaxLength, int depth) {
        List<CodeBlock> result = new ArrayList<>();
        boolean isIfScope = scope instanceof IfScope;

        if(isIfScope) {
            if(maxLength < 6) {
                splittedCode.put(
                        splitScope(scope, codeHeaderName, splittedCode, functionMaxLength, functionMaxLength, depth + 1),
                        depth + 1
                );

                result.add(new CallFunctionBlock(codeHeaderName + "_" + (splittedCode.size() + 1)));
                return result;
            }

            maxLength -= 4;
            result.addAll(((IfScope) scope).getStart());
        }

        maxLength -= 2;

        List<CodeBlock> content = scope.getContent();
        List<CodeBlock> lastCodeLine = null;

        int iterationStartIndex = 0;
        int iterationBlocksLen = 0;

        int i = 0;
        for(CodeBlock block : content) {
            boolean isBlockScope = block instanceof Scope;

            if(isBlockScope) {
                iterationBlocksLen += ((Scope) block).totalLength;
            } else {
                iterationBlocksLen += 2;
            }

            if(iterationBlocksLen >= maxLength) {
                List<CodeBlock> codeLine = new ArrayList<>(content.subList(iterationStartIndex, i));
                iterationStartIndex = i;

                if(isBlockScope) {
                    Scope scopeBlock = (Scope) block;
                    int scopeMaxLen = maxLength - (iterationBlocksLen - scopeBlock.totalLength);

                    codeLine.addAll(splitScope(scopeBlock, codeHeaderName, splittedCode, scopeMaxLen, functionMaxLength, depth));
                    iterationStartIndex++;
                }

                maxLength = functionMaxLength;
                iterationBlocksLen = 0;

                if(lastCodeLine == null) {
                    int startIndex = result.size();
                    result.addAll(codeLine);

                    lastCodeLine = result.subList(startIndex, result.size());
                } else {
                    if(codeLine.size() == 1) {
                        lastCodeLine.addAll(codeLine);
                    } else {
                        splittedCode.put(codeLine, ++depth);

                        lastCodeLine.add(new CallFunctionBlock(codeHeaderName + "_" + (splittedCode.size() + 1)));
                        lastCodeLine = codeLine;
                    }
                }
            }

            i++;
        }

        List<CodeBlock> codeLine = new ArrayList<>(content.subList(iterationStartIndex, content.size()));

        if(codeLine.size() != 0) {
            if(lastCodeLine == null) {
                result.addAll(codeLine);
            } else {
                if(codeLine.size() == 1) {
                    lastCodeLine.addAll(codeLine);
                } else {
                    splittedCode.put(codeLine, ++depth);

                    lastCodeLine.add(new CallFunctionBlock(codeHeaderName + "_" + (splittedCode.size() + 1)));
                }
            }
        }

        if(isIfScope) {
            result.add(((IfScope) scope).getEnd());
        }

        return result;
    }

    private static List<CodeBlock> unwrapScope(Scope scope) {
        List<CodeBlock> scopeContent = new ArrayList<>();

        for (CodeBlock block : scope.content) {
            if(block instanceof Scope) {
                scopeContent.addAll(unwrapScope((Scope) block));
            } else {
                scopeContent.add(block);
            }
        }

        return scopeContent;
    }

    private static List<CodeBlock> unwrapAndUpdateReturn(Scope scope, int depth) {
        List<CodeBlock> unwrappedBlocks = unwrapScope(scope);

        if(depth != 0) {
            List<CodeBlock> updatedBlocks = new ArrayList<>(unwrappedBlocks);

            for (int i = 0; i < unwrappedBlocks.size(); i++) {
                CodeBlock block = unwrappedBlocks.get(i);

                if (block instanceof ControlBlock) {
                    if (((ControlBlock) block).getAction().equals("Return")) {
                        updatedBlocks.set(
                                i,
                                new ControlBlock("ReturnNTimes")
                                        .SetItems(new Number().Set(depth + 1))
                        );
                    }
                }
            }

            return updatedBlocks;
        }

        return unwrappedBlocks;
    }
}
