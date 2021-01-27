package twitter;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.*;
class Tweet
{
	/*
	 * Instance variables that uniquely identifies a tweet is its id
	 * Attributes => tweet text and and also list of hashtags
	 * Can be extended for no of retweets or likes, etc
	 */
	private int tweetId;
	private String tweetText;
	private List<String> hashTag;
	Tweet(int id , String tweetText){
		this.tweetId = id;
		this.tweetText = tweetText;
		hashTag = new ArrayList<>();
	}
	/*
	 * Parse Tweet Text and retreive all the hashtags.
	 */
	public void parseTweet(){
		//regex for parsing hashTag
		String patternStr = "(?:\\s|\\A)[##]+([A-Za-z0-9-_]+)";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(tweetText);
		String result = "";
		// Search for Hashtags in the tweet text
	    while (matcher.find()) {
		   result = matcher.group();
		   result = result.replace(" ", "");
	       String search = result.replace("#", "");
	       if(search.length() != 0)
	    	   hashTag.add(search.trim());
	     }
	}
	/*
	 * Returns all the hashtags for a particular tweettext
	 */
	public List<String> getHashTags(){
		return this.hashTag;
	}
	/*
	 * 
	 * Gets the tweetId for a  particular Tweet
	 */
	public int getTweetId(){
		return this.tweetId;
	}
}