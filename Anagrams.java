import java.util.*;

/*
   Tsaqif Insani 
   CS 145
   06/13/2022
   Program to create anagrams for a given phrase based on the dictionary that is passed at construction. 
*/

public class Anagrams{
   private Set<String> dictionary;

   public Anagrams(Set<String> dictionary){
      if(dictionary == null){
         throw new IllegalArgumentException("dictionary cannot be null");
      }else{
         this.dictionary = dictionary;
      }
   }

   //returns a set of all the words that are contained in the letters of passed phrase 
   public Set<String> getWords(String phrase){
      if(phrase == null){
         throw new IllegalArgumentException("phrase cannot be null");
      }else{
         return getWordsHelper(phrase);
      }
   }
   
   //get Words additional function 
   private Set<String> getWordsHelper(String phrase){
      Set<String> validWords = new TreeSet<String>();
      LetterInventory testPhrase = new LetterInventory(phrase);
         for(String i : dictionary){
            LetterInventory dictWord = new LetterInventory(i);
               if(testPhrase.contains(dictWord)){
                  validWords.add(i);
               }
         }
      return validWords;
   }
   
   
   //prints out all the anagrams that could be made 
   public void print(String phrase){
      Set<String> validWords = getWords(phrase);
      LetterInventory convertPhrase = new LetterInventory(phrase);
      List<String> choice = new LinkedList<String>();
      print(convertPhrase, validWords, choice, -1);
   }
   
   
   //helper method to accept additional data as parameters 
   private void print(LetterInventory phrase, Set<String> validWords, List<String> choice, int max){
      if(phrase.isEmpty()){
         System.out.println(choice);
      }else if(max != 0){
         for(String i : validWords){
            if(i.length() <= phrase.size() && phrase.contains(i)){
               choice.add(i);
               phrase.subtract(i);
               print(phrase, validWords, choice, max - 1);
               phrase.add(i);
               choice.remove(i);
            }
         }
      }
   }
   
   //prints out all the anagrams that could be made that contain max amount of words or less. 
   public void print(String phrase, int max){
      if(max == 0){
         max = -1;
      }
      Set<String> validWords = getWords(phrase);
      LetterInventory convertPhrase = new LetterInventory(phrase);
      List<String> choice = new ArrayList<String>();
      print(convertPhrase, validWords, choice, max);
   }
}