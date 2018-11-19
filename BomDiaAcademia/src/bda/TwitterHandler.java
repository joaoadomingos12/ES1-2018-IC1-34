import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
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
	private Twitter twitter;


	public ArrayList <Postt> abcd = new ArrayList<Postt>();
	List<Status> statuses= new ArrayList<Status>();
	public float tempo = 5000 * 60 * 1000;
	//<<<<<<< Upstream, based on branch 'master' of https://github.com/joaoadomingos12/ES1-2018-IC1-34.git

	/**
	 * Construtor
	 * @param cfg
	 */




	public TwitterHandler(Config cfg) {
		this.cfg=cfg;	
		config();
	}

	/**
	 * Lista os tweets da timeline do utilizador
	 */
	public void config () {
		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			.setOAuthConsumerKey(cfg.getTwitterKey())
			.setOAuthConsumerSecret(cfg.getTwitterSecret())
			.setOAuthAccessToken(cfg.getTwitterToken())
			.setOAuthAccessTokenSecret(cfg.getTwitterTokenSecret());
			TwitterFactory tf = new TwitterFactory(cb.build());
			twitter = tf.getInstance();        		

			statuses = twitter.getHomeTimeline();
		}catch (Exception e) { System.out.println(e.getMessage()); 
		}
	}
	public ArrayList<Postt> listPosts() {


		for (Status st : statuses) {
			
			//window.DefaultResultado.addElement(a);
			long tenAgo = (long) (System.currentTimeMillis() - tempo);
			System.out.println(tenAgo + "tenago");
			System.out.println(st.getCreatedAt().getTime()+ "1º tempo");
    		if (st.getCreatedAt().getTime() >tenAgo) {
    		    System.out.println("searchTimestamp is older than 10 minutes");
    		    Postt a = new Postt (st.getId(), st.getText(),st.getCreatedAt(),"Twitter");
    		    abcd.add(a);
    		}
			
		}


		for (Status a : statuses) {
	        		
	        	}
		//	        System.out.println("------------------------\n Showing home timeline \n------------------------");
		//			int counter=0;
		//			int counterTotal = 0;
		//	        for (Status status : statuses) {
		//				// Filters only tweets from user "catarina"
		//				if (status.getUser().getName() != null && status.getUser().getName().contains("catarina")) {
		//					System.out.println(status.getUser().getName() + ":" + status.getText());
		//					counter++;
		//				}
		//				counterTotal++;
		//	        }
		//System.out.println("-------------\nN� of Results: " + counter+"/"+counterTotal);

		System.out.println("aqui");

		//window.transform(window.DefaultResultado);
		//window.list.setModel( window.DefaultResultado);
		return abcd;

	}
	public void retweet(long id) {
		try {
			for (Status st : statuses) {
				if (st.getId()==id) {
					twitter.retweetStatus(st.getId());
				}
			}

			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
