package com.neuralnoise.opennlp;

import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.Arrays;

import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

public class App {

	public static void sentenceDetect() throws Exception {
		String paragraph = "Ciao. Come stai ? Io sono Michele.";
		// always start with a model, a model is learned from training data
		InputStream is = App.class.getResourceAsStream("/models/it/it-sent.bin");  
		SentenceModel model = new SentenceModel(is);
		SentenceDetectorME sdetector = new SentenceDetectorME(model);
		String sentences[] = sdetector.sentDetect(paragraph);
		System.out.println(Arrays.toString(sentences));
		is.close();
	}
	
	public static void tokenize() throws Exception {
		String paragraph = "Ciao. Come stai ? Io sono Michele.";
		InputStream is = App.class.getResourceAsStream("/models/it/it-token.bin");
		TokenizerModel model = new TokenizerModel(is);
		Tokenizer tokenizer = new TokenizerME(model);
		String tokens[] = tokenizer.tokenize(paragraph);
		for (String a : tokens)
			System.out.println(a);
		is.close();
	}
	
	public static void posTag() throws Exception {
		URL res = App.class.getResource("/models/it/it-pos-maxent.bin");
		POSModel model = new POSModelLoader().load(new File(res.getPath()));
		POSTaggerME tagger = new POSTaggerME(model);
		String paragraph = "Ciao. Come stai ? Io sono Michele.";
		ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(paragraph));
		String line;
		while ((line = lineStream.read()) != null) {
			String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(line);
			String[] tags = tagger.tag(whitespaceTokenizerLine);
			POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
			System.out.println(sample.toString());
		}
	}
	
	public static void main(String[] args) throws Exception {
		sentenceDetect();
		tokenize();
		posTag();
	}

}
