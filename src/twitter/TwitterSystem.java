/**
 * 
 */
package twitter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
public class TwitterSystem {


	static HashMap<String , Integer> globalCountHashTags; // keeps the count of occurred hashtags and their count
	static HashMap<Integer, Tweet> tweetMap; // map to contain tweetNo and corresponding tweet object
	static PriorityQueue<Map.Entry<String, Integer>> frequentHashTags; // top 10 hashtags with maximum occurences
	static{
		globalCountHashTags = new HashMap<>();
		tweetMap = new HashMap<>();
		frequentHashTags = new PriorityQueue<>((a,b) -> a.getValue()==b.getValue() ? b.getKey().compareTo(a.getKey()) : b.getValue()-a.getValue());
	}
	
	/*
	 * Keep a global count of each hashtag in the system
	 */
	static void updateMap(List<String> hashTags ){
		
		for(String tags : hashTags){
			globalCountHashTags.put(tags, globalCountHashTags.getOrDefault(tags, 0) + 1);
		}
	}
	
	/*
	 * Utility to print what is the count of eachhashtag
	 */
	static void printMap() {
		for(String map : globalCountHashTags.keySet()){
		System.out.println(map +" "+ globalCountHashTags.get(map));
		}
	}
	
	/*
	 * Set only the top 10 hashtags in the system using a min Heap
	 * Core logic to store the maximum occurring hashtags
	 */
	static void setTop10HashTags(){
		for(Map.Entry<String, Integer> entry: globalCountHashTags.entrySet()){
            frequentHashTags.offer(entry);
            if(frequentHashTags.size()>10)
                frequentHashTags.poll();
        }
	}
	
	/*
	 * Driver method.
	 */
	public static void main(String[] args) {
		System.out.println("**************Welcome to Twitter *********************");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("*********************Please enter the no of tweets the system can hold*********************");
		try{	
			int numberOfTweets = Integer.parseInt(br.readLine());
			for(int i =  1 ; i<=numberOfTweets ;i++){	
				System.out.println("Please Enter your tweet text for Tweet No =======> "+i);
				String tweetText = br.readLine(); // get the tweet text
				Tweet tweet = new Tweet(i, tweetText); 
				tweet.parseTweet(); //parse the tweet for hashtags
				List<String> hashTags = tweet.getHashTags(); // get the stored hashtags
				tweetMap.put(i , tweet); // update the global tweetMap
				updateMap(hashTags);		
			}
		}
		catch(IOException ex){
				System.out.println(String.valueOf(ex));
		}
		//printMap();
		setTop10HashTags();
		System.out.println("---- Here are your top 10 Common hashtags ------- ");
		/*
		 * Loop to print out the top 10 hashtags in the system
		 */
		System.out.println("***************Top 10 HashTags***********************");
		while(!frequentHashTags.isEmpty()) {
			Map.Entry<String , Integer> exit = frequentHashTags.poll();
			System.out.println(exit.getKey()+"==============> "+exit.getValue());
		}
	}

}
