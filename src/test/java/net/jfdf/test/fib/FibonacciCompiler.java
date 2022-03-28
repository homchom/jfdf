package net.jfdf.test.fib;

import net.jfdf.compiler.JFDFCompiler;
import net.jfdf.compiler.library.InvokeVirtual;
import net.jfdf.compiler.library.References;
import net.jfdf.jfdf.blocks.*;
import net.jfdf.jfdf.mangement.CodeManager;
import net.jfdf.test.compiler.AStar;
import net.jfdf.test.compiler.AStarTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class FibonacciCompiler {
    private final static boolean sendItems = true;
    private final static boolean sendEvents = true;
    private final static boolean showTemplateBase64 = true;

    private final static int sendCooldown = 200;
    private final static String filterByNameRegex = "";
    private final static boolean sendStd = false;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        JFDFCompiler.compileClass(FibonacciListener.class);
        JFDFCompiler.compileClass(FibonacciSequence.class);

        String[] codeOutputArray = CodeManager.instance.getCodeValues();
        System.out.println("Took " + (System.currentTimeMillis() - startTime) + "ms to compile the code");

        try {
            System.out.println("Current Phase: Init Socket...");

            Socket socket = null;
            if(sendItems) socket = new Socket("127.0.0.1", 31372);

            System.out.println("Current Phase: Sending Templates...");
            CodeHeader[] codeHeaders = CodeManager.instance.getCodeHeaders().toArray(CodeHeader[]::new);
            for (int i = 0; i < codeOutputArray.length; i++) {
                String codeOutput = codeOutputArray[i];
                CodeHeader codeHeader = codeHeaders[i];

                if (sendEvents || !(codeHeader instanceof PlayerEventBlock || codeHeader instanceof EntityEventBlock)) {
                    String name = null;

                    if (codeHeader instanceof ProcessBlock) {
                        name = ((ProcessBlock) codeHeader).getName();
                    } else if (codeHeader instanceof FunctionBlock) {
                        name = ((FunctionBlock) codeHeader).getName();
                    }

                    if(name != null) {
                        if (!filterByNameRegex.equals("") && !name.matches(filterByNameRegex)) {
                            continue;
                        } else if(!sendStd && name.startsWith("_jfdf>std>")) {
                            continue;
                        }
                    }

                    System.out.println("-=-=-=-  A Code Template " + i + " -=-=-=-");
                    System.out.println("Template Name: " + codeHeader.getTemplateName());
                    System.out.println("Template Name (with colors): " + codeHeader.getTemplateNameWithColors());
                    if (showTemplateBase64) printSplit(codeOutput, 250);

                    if (sendItems) {
                        System.out.println("Sending item...");

                        String sendDataJson = "{\"type\":\"template\",\"source\":\"" +
                                "\u00A76\u00A7lJFDF » \u00A7eTic Tac Toe" +
                                "\",\"data\":\"{\\\"name\\\":\\\"" + codeHeader.getTemplateNameWithColors().replace("\"", "\\\\\"") + "\\\",\\\"data\\\":\\\"" +
                                codeOutput +
                                "\\\"}\"}";

                        InputStream inputStream = socket.getInputStream();
                        OutputStream stream = socket.getOutputStream();
                        stream.write(sendDataJson.getBytes(StandardCharsets.ISO_8859_1));
                        stream.write('\n');

                        StringBuilder res = new StringBuilder();
                        while (true) {
                            char readChar = (char) inputStream.read();

                            if (readChar == '\n') {
                                break;
                            } else {
                                res.append(readChar);
                            }
                        }

                        System.out.println("Response: " + res.toString());

                        TimeUnit.MILLISECONDS.sleep(sendCooldown);
                    }
                }
            }

            if(sendItems) {
                socket.close();
                System.out.println("Sending Templates finished !");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printSplit(String string, int n) {
        int strLen = string.length();

        for (int i = 0; i < strLen;) {
            System.out.println(string.substring(i, Math.min(i += n, strLen)));
        }
    }
}
