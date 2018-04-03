package com.classifier;

import java.io.IOException;
import java.util.List;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

public class NERExtractor
{
	public static void main(String[] args) throws IOException
	{
		String serializedClassifier = "classifiers/english.muc.7class.distsim.crf.ser.gz";
		CRFClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
		String text = "The Government of Donald Trump in Tunisia won the contract for 10000 pounds for the United Nations";
		List<List<CoreLabel>> classify = classifier.classify(text);
		System.out.println(classify);
		for (List<CoreLabel> coreLabels : classify)
		{
			for (CoreLabel coreLabel : coreLabels)
			{
				String word = coreLabel.word();
				String category = coreLabel.get(CoreAnnotations.AnswerAnnotation.class);
				System.out.println(word +" "+category);
			}
		}
		
		AbstractSequenceClassifier<CoreLabel> classifie = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
		//System.out.println(classifie.classifyToString(text, "inlineXML", true));
		classifie.classifyAndWriteAnswers("one.txt");
		//classifie.
	}
}
