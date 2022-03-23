package net.jfdf.jfdf.mangement;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;
import java.util.zip.GZIPOutputStream;

import net.jfdf.jfdf.AddonsManager;
import net.jfdf.jfdf.JFDFAddon;
import net.jfdf.jfdf.blocks.CodeBlock;
import net.jfdf.jfdf.blocks.CodeHeader;
import net.jfdf.jfdf.values.Variable;
import net.jfdf.jfdf.values.Variable.Scope;

public class CodeManager {
	private final List<Variable> variables = new ArrayList<>();
	private final List<Method> functionsWithArgs = new ArrayList<>();
	private final LinkedHashMap<CodeHeader, List<CodeBlock>> codeBlocks = new LinkedHashMap<>();
	private String author = "net.jfdf.jfdf";

	public PLOT_SIZE plotSize;
	public PLAYER_RANK playerRank;

	public final static CodeManager instance = new CodeManager();

	public Variable getVariable(String variableName, Scope scope) {
		for (Variable variable : variables) {
			if (variable.getName().equals(variableName) && variable.getScope().equals(scope))
				return variable;
		}

		Variable variable = new Variable(variableName, scope);
		variables.add(variable);
		return variable;
	}
	
	public void addCodeBlock(CodeBlock codeBlock) {
		@SuppressWarnings("unchecked")
		List<CodeBlock>[] array = (List<CodeBlock>[]) new List[codeBlocks.size()];
		this.codeBlocks.values().toArray(array)[codeBlocks.size() - 1].add(codeBlock);

		try {
			JFDFAddon.class.getMethod("onAddCodeBlock", CodeBlock.class).invoke(null, codeBlock);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException ignored) {}
	}

	public List<CodeBlock> getActiveCodeBlocks() {
		@SuppressWarnings("unchecked")
		List<CodeBlock>[] array = (List<CodeBlock>[]) new List[codeBlocks.size()];
		return this.codeBlocks.values().toArray(array)[codeBlocks.size() - 1];
	}
	
	public void addCodeBlocks(CodeHeader codeHeader, CodeBlock... codeBlocks) {
		this.codeBlocks.put(codeHeader, Arrays.asList(codeBlocks));
	}
	
	public void addCodeBlocks(CodeHeader codeHeader, ArrayList<CodeBlock> codeBlocks) {
		this.codeBlocks.put(codeHeader, codeBlocks);
	}

	public void addFunctionWithArgs(Method functionMethod) {
		functionsWithArgs.add(functionMethod);
	}

	public Method[] getFunctionsWithArgs() {
		return functionsWithArgs.toArray(new Method[0]);
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public List<String> getCodeValuesAsList() {
		Map<CodeHeader, List<CodeBlock>> addonCodeBlocks = new HashMap<>();
		List<String> codeValues = new ArrayList<>();
		
		for (Entry<CodeHeader, List<CodeBlock>> codeBlocksData : codeBlocks.entrySet()) {
			CodeHeader codeHeader = codeBlocksData.getKey();
			List<CodeBlock> codeBlocks = new ArrayList<>(codeBlocksData.getValue());

			addonCodeBlocks.putAll(AddonsManager.publishPreGenerateLine(codeHeader, codeBlocks));
			
			String codeNBT = "{\"blocks\":[";
			
			List<String> codeBlocksJSON = new ArrayList<>();
			codeBlocksJSON.add(codeHeader.asJSON());
			
			for (CodeBlock codeBlock : codeBlocks) {
				codeBlocksJSON.add(codeBlock.asJSON());
			}
			
			codeNBT += String.join(",", codeBlocksJSON) + "]}";
			
	        try {
				ByteArrayOutputStream bos = new ByteArrayOutputStream(codeNBT.length());
				GZIPOutputStream gzip = new GZIPOutputStream(bos);

				gzip.write(codeNBT.getBytes(StandardCharsets.UTF_8));
				gzip.close();
				bos.close();

				String compressed = new String(Base64.getEncoder().encode(bos.toByteArray()), StandardCharsets.UTF_8);
				codeValues.add(compressed);
				
				try {
					JFDFAddon.class.getMethod("onGetCodeHeaderValue", String.class).invoke(null, compressed);
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {}
	        } catch (Exception t) {
	        	t.printStackTrace();
	        	throw new RuntimeException("Error while compressing");
			}
		}

		for (Entry<CodeHeader, List<CodeBlock>> codeBlocksData : addonCodeBlocks.entrySet()) {
			CodeHeader codeHeader = codeBlocksData.getKey();
			List<CodeBlock> codeBlocks = codeBlocksData.getValue();

			String codeNBT = "{\"blocks\":[";
			List<String> codeBlocksJSON = new ArrayList<>();

			codeBlocksJSON.add(codeHeader.asJSON());

			for (CodeBlock codeBlock : codeBlocks) {
				codeBlocksJSON.add(codeBlock.asJSON());
			}

			codeNBT += String.join(",", codeBlocksJSON) + "]}";

			try {
				ByteArrayOutputStream bos = new ByteArrayOutputStream(codeNBT.length());
				GZIPOutputStream gzip = new GZIPOutputStream(bos);

				gzip.write(codeNBT.getBytes(StandardCharsets.UTF_8));
				gzip.close();
				bos.close();

				String compressed = new String(Base64.getEncoder().encode(bos.toByteArray()), StandardCharsets.UTF_8);
				codeValues.add(compressed);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Error while compressing");
			}
		}
        
        return codeValues;
	}
	
	public String[] getCodeValues() {
		List<String> codeValuesList = getCodeValuesAsList();
		String[] codeValues = new String[codeValuesList.size()];
		return codeValuesList.toArray(codeValues);
	}

	public List<String> getCodeValuesJSONAsList() {
		List<String> jsons = new ArrayList<>();

		for (Entry<CodeHeader, List<CodeBlock>> codeBlocksData : codeBlocks.entrySet()) {
			CodeHeader codeHeader = codeBlocksData.getKey();
			List<CodeBlock> codeBlocks = codeBlocksData.getValue();

			String codeNBT = "{\"blocks\":[";

			List<String> codeBlocksJSON = new ArrayList<>();

			codeBlocksJSON.add(codeHeader.asJSON());

			for (CodeBlock codeBlock : codeBlocks) {
				codeBlocksJSON.add(codeBlock.asJSON());
			}

			codeNBT += String.join(",", codeBlocksJSON) + "]}";
			jsons.add(codeNBT);
		}

		return jsons;
	}

	public String[] getCodeValuesJSON() {
		List<String> codeValuesJsonList = getCodeValuesJSONAsList();
		String[] codeValues = new String[codeValuesJsonList.size()];
		return codeValuesJsonList.toArray(codeValues);
	}
	
	public List<String> getGiveCommandsAsList() {
		ArrayList<String> giveCommands = new ArrayList<>();
		List<String> codeValues = getCodeValuesAsList();
		
		int i = 0;
		for (Entry<CodeHeader, List<CodeBlock>> codeBlocksPair : codeBlocks.entrySet()) {
			CodeHeader codeHeader = codeBlocksPair.getKey();
			String codeValue = codeValues.get(i);
			
			String giveCommand = "/give @p ender_chest{PublicBukkitValues: {\"hypercube:codetemplatedata\": '{\"author\":\"" + author + "" + "\",\"name\":\"" + codeHeader.getTemplateName() + "\",\"version\":1,\"code\":\"" + codeValue  + "\"}'}, display: {Name: '{\"text\":\"" + codeHeader.getTemplateNameWithColors() + "\"}'}} 1";
			giveCommands.add(giveCommand);
			i++;
		}
		return giveCommands;
	}
	
	public enum PLAYER_RANK {
		NO_RANK,
		NOBLE,
		EMPEROR,
		MYTHIC,
		OVERLORD
	}
	
	public enum PLOT_SIZE {
		BASIC,
		LARGE,
		MASSIVE
	}

	public Set<CodeHeader> getCodeHeaders() {
		return codeBlocks.keySet();
	}

	public List<CodeBlock> getCodeHeaderBlocks(CodeHeader codeHeader) {
		return codeBlocks.get(codeHeader);
	}
}
