import java.sql.Date;
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
	private Gui window;
	private static final float TEN_MINUTES = 10 * 60 * 1000;
	//<<<<<<< Upstream, based on branch 'master' of https://github.com/joaoadomingos12/ES1-2018-IC1-34.git

		/**
		 * Construtor
		 * @param cfg
		 */
	



		public TwitterHandler(Config cfg, Gui window) {
			this.cfg=cfg;	
			this.window=window;
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

				for (Status st : statuses) {
					Postt a = new Postt (st.getUser().getName(), st.getText(),st.getCreatedAt(),"Twitter");
					window.DefaultResultado.addElement(a);
				}



				//twitter.retweetStatus(statuses.get(13).getId());

				/*for (Status a : statuses) {
	        		long tenAgo = (long) (System.currentTimeMillis() - TEN_MINUTES);
	        		if (a.getCreatedAt().getTime() >tenAgo) {
	        		    System.out.println("searchTimestamp is older than 10 minutes");
	        		}
	        	}*/
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
			} catch (Exception e) { System.out.println(e.getMessage()); }
			System.out.println("aqui");

			//window.DefaultResultado.addElement(a);
			window.transform(window.DefaultResultado);
			window.list.setModel( window.DefaultResultado);

		}

	}
