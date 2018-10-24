import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;


public class FacebookHandler {
	
	private FacebookClient fbClient;
	private Gui window ;
	private Postt post;
	private Date date;
	
	public FacebookHandler(String token, Gui window){
		this.window=window;
		fbClient = new DefaultFacebookClient(token);
	}
	
	public void listPosts(String search) {
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		System.out.println("\nPosts:");
		int counter5 = 0;
		int counterTotal = 0;
		for (List<Post> page : result) {
	for (Post aPost : page) {
				// Filters only posts that contain the word "Inform"
				if (aPost.getMessage() != null && aPost.getMessage().contains(search)) {
					post = new Postt (aPost.getId(),aPost.getMessage(),aPost.getCreatedTime(),"Facebook");
			window.DefaultResultado.addElement(post);
			
			window.transform(window.DefaultResultado);
			window.list.setModel(window.DefaultResultado);
				
//			System.out.println(post.toString());
//					System.out.println("---- Post "+ counter5 + " ----");
//					System.out.println("Id: "+"fb.com/"+aPost.getId());
//					System.out.println("Message: "+aPost.getMessage());
//					System.out.println("Created: "+aPost.getCreatedTime());
					
					counter5++;
				}
				counterTotal++;
		}
	}
		
		
		System.out.println(window.DefaultResultado.getSize()+ ",jkldasjlas");
		window.list.setModel(window.DefaultResultado);
		//fbClient5.publish(arg0, arg1, arg2);

		System.out.println("-------------\nN� of Results: " + counter5+"/"+counterTotal);		
	}
	
}
