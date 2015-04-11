package com.HotFlow.TribeCraft.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConstructor;
import org.bukkit.configuration.file.YamlRepresenter;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

/**
 * @author HotFlow
 */
public class TribeConfiguration extends YamlConfiguration {
	private final DumperOptions yamlOptions = new DumperOptions();
	private final Representer yamlRepresenter = new YamlRepresenter();
	private final Yaml yaml = new Yaml(new YamlConstructor(),
			this.yamlRepresenter, this.yamlOptions);

	public TribeConfiguration() {
		super();
	}

	/**
	 * 保存config
	 * 
	 * @param file
	 *            文件
	 * @throws IOException
	 */
	@Override
	public void save(File file) throws IOException {
		this.createParentDirs(file);

		String data = saveToString();

		FileWriter writer = new FileWriter(file);
		try {
			writer.write(data);
		} finally {
			writer.close();
		}
	}

	/**
	 * 保存config
	 * 
	 * @param file
	 *            文件路径
	 * @throws IOException
	 */
	@Override
	public void save(String file) throws IOException {
		save(new File(file));
	}

	/**
	 * 载入
	 * 
	 * @param file
	 * @throws java.io.FileNotFoundException
	 * @throws org.bukkit.configuration.InvalidConfigurationException
	 */
	@Override
	public void load(File file) throws FileNotFoundException, IOException,
			InvalidConfigurationException {
		load(new FileInputStream(file));
	}

	/**
	 * 载入
	 * 
	 * @param stream
	 * @throws IOException
	 * @throws InvalidConfigurationException
	 */
	@Override
	public void load(InputStream stream) throws IOException,
			InvalidConfigurationException {
		InputStreamReader reader = new InputStreamReader(stream);
		StringBuilder builder = new StringBuilder();
		BufferedReader input = new BufferedReader(reader);
		ArrayList<String> builders = new ArrayList<String>();
		try {
			String line;
			while ((line = input.readLine()) != null) {
				builders.add(line);
				builder.append(line);
				builder.append('\n');
			}
		} finally {
			input.close();
		}

		super.loadFromString(builder.toString());
	}

	/**
	 * 载入 config
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InvalidConfigurationException
	 */
	@Override
	public void load(String file) throws FileNotFoundException, IOException,
			InvalidConfigurationException {
		load(new File(file));
	}

	/**
	 * 修复1.7.10不能为中文
	 * 
	 * @param contents
	 * @throws org.bukkit.configuration.InvalidConfigurationException
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void loadFromString(String contents)
			throws InvalidConfigurationException {
		Map input;
		try {
			input = (Map) this.yaml.load(contents);
		} catch (YAMLException e) {
			throw new InvalidConfigurationException(e);
		} catch (ClassCastException e) {
			throw new InvalidConfigurationException("Top level is not a Map.");
		}

		String header = parseHeader(contents);
		if (header.length() > 0) {
			options().header(header);
		}

		if (input != null) {
			convertMapsToSections(input, this);
		}
	}

	/**
	 * 修复1.7.10不能为中文
	 * 
	 * @return
	 */
	@Override
	public String saveToString() {
		this.yamlOptions.setIndent(options().indent());
		this.yamlOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		this.yamlRepresenter.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

		String header = buildHeader();
		String dump = this.yaml.dump(getValues(false));

		if (dump.equals("{}\n")) {
			dump = "";
		}

		return header + dump;
	}

	/**
	 * 创建父文件夹
	 * 
	 * @param file
	 * @throws IOException
	 */
	private void createParentDirs(File file) throws IOException {
		File parent = file.getCanonicalFile().getParentFile();
		if (parent == null) {
			return;
		}

		parent.mkdirs();

		if (!parent.isDirectory()) {
			throw new IOException("Unable to create parent directories of "
					+ file);
		}
	}

}
