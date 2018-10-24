import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * 
 *
 */
public class TwitterHandler {
	
	private String consumerKey;
	private String consumerSecret;
	private String token;
	private String tokenSecret;
	private Config cfg;
	
	/**
	 * Construtor
	 * @param cfg
	 */
	public TwitterHandler(Config cfg) {
		this.cfg=cfg;	
	}
	
	/**
	 * Lista os tweets da timeline do utilizador
	 */
	public void listPosts() {
		try {
	    	ConfigurationBuilder cb = new ConfigurationBuilder();
	    	cb.setDebugEnabled(true)
	    	  .setOAuthConsumerKey(cfg.getTwitterKey())
	    	  .setOAuthConsumerSecret(cfg.getTwitterSecret())
	    	  .setOAuthAccessToken(cfg.getTwitterToken())
	    	  .setOAuthAccessTokenSecret(cfg.getTwitterTokenSecret());
	    	TwitterFactory tf = new TwitterFactory(cb.build());
	    	Twitter twitter = tf.getInstance();        		
	        List<Status> statuses = twitter.getHomeTimeline();
	        //twitter.retweetStatus(statuses.get(13).getId());
	        
	        System.out.println("------------------------\n Showing home timeline \n------------------------");
			/*int counter=0;
			int counterTotal = 0;
	        for (Status status : statuses) {
				// Filters only tweets from user "catarina"
				if (status.getUser().getName() != null && status.getUser().getName().contains("catarina")) {
					System.out.println(status.getUser().getName() + ":" + status.getText());
					counter++;
				}
				counterTotal++;
	        }*/
			//System.out.println("-------------\nNº of Results: " + counter+"/"+counterTotal);
	    } catch (Exception e) { System.out.println(e.getMessage()); }
	}
	
}
