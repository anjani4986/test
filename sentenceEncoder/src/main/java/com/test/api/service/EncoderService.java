package com.test.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.api.model.ContentWrapper;
import com.test.api.model.EncodedContent;

@Service("encoderService")
public class EncoderService {
	public static HashMap<String, String> hm= new HashMap<String, String>();
	public static String[][] stringPool = {{"run","jog","sprint"},{"hi","hello"},{"bye","tata"}};
	public static String[][] stringPoolLanguage = {{"run","xyzz","jjjj"},{"hi","ciao"},{"bye","sayo nara"}};
	public static String[] stopWordList = {"a","an","the","on","is","this","that","so"};
	public ContentWrapper encode(ContentWrapper wrapper) {
		List<EncodedContent> result = new ArrayList<EncodedContent>();
        for (EncodedContent content: wrapper.getEncodedcontents()) {
        	HashMap<EncodedContent, Integer> hm =  new HashMap<>();
        	preProcess(content.getContent());
           
        }
		return wrapper;
	}

	private void preProcess(String s) {
		List<String> wordList = new ArrayList<String>();
		wordList.addAll(wordList);
		wordList= LowerCaseConverter(wordList);
		wordList= RemoveStopWords(wordList);
		wordList= convertToRoot(wordList);
		 HashMap<String, List<String>> hm1= new HashMap<String, List<String>>();
		for(String str:wordList) {
			List<String> result = getSameMeaningWord(str);
			List temp1 = new ArrayList<String>();
			for(String s1: result) {
				List temp = getWordsInDifferentLanguages( s1);
				temp1.addAll(temp);
			}
			result.addAll(temp1);
			hm1.put(str, result);
		}
		hm.put(s,createVector(hm1));
	}

	private List<String> LowerCaseConverter(List<String> wordList) {
		List<String> lowerList = new ArrayList<String>();
		for(String s:wordList) {
			lowerList.add(s.toLowerCase());
		}
		return lowerList;
	}

	private List<String> getSameMeaningWord(String str) {
		int len = stringPool.length;
		for(int i=0; i<len; i++) {
			String[] temp = stringPool[i];
			int len1 = temp.length;
			for(int j=0; j<len1; j++) {
				if(temp[j].equals(str)) {
					ArrayList<String> l = new ArrayList<String>();
					for(int k=0; k<len1; k++) {
						l.add(temp[k]);
					}return l;
				}
			}
		}
		return null;
	}

	private List<String> getWordsInDifferentLanguages(String str) {
		int len = stringPoolLanguage.length;
		for(int i=0; i<len; i++) {
			String[] temp = stringPoolLanguage[i];
			int len1 = temp.length;
			for(int j=0; j<len1; j++) {
				if(temp[j].equals(str)) {
					ArrayList<String> l = new ArrayList<String>();
					for(int k=0; k<len1; k++) {
						l.add(temp[k]);
					}return l;
				}
			}
		}
		return null;
	}
 
	private String createVector(HashMap<String, List<String>> hm1) {
		
		// TODO Auto-generated method stub
		return null;
	}

	private List<String> convertToRoot(List<String> wordList) {
		List<String> result = new ArrayList<String>();
		for(String s:wordList) {
			if(s.endsWith("ing")) {//will convert going to root word go
				if (s.length() > 3 ) {
					s = s.substring(s.length() - 3 );}else {
						result.add(s);
					}
				result.add(s);
			}else {
				result.add(s);
			}
		}
		return null;
	}

	private List<String> RemoveStopWords(List<String> list) {
		for(String s:list) {
			int len = stopWordList.length;
			for(int i=0; i<len; i++) {
				if(s.equals(stopWordList[i])) {
					list.remove(s);
				}
			}
		}
		return list;
	}

}
