package ifba.inf011.template_method;

import ifba.inf011.template_method.chainstyle.FacebookPostHandler;
import ifba.inf011.template_method.chainstyle.PostHandler;
import ifba.inf011.template_method.chainstyle.TwitterPostHandler;
import ifba.inf011.template_method.classic.FacebookNetwork;
import ifba.inf011.template_method.classic.Network;
import ifba.inf011.template_method.classic.TwitterNetwork;
import ifba.inf011.template_method.domain.SocialPost;
import ifba.inf011.template_method.factory_method.FacebookPublisherApplication;
import ifba.inf011.template_method.factory_method.SocialPublisherApplication;
import ifba.inf011.template_method.factory_method.TwitterPublisherApplication;
import ifba.inf011.template_method.hooks.FacebookPublishingWorkflow;
import ifba.inf011.template_method.hooks.PublishingWorkflow;
import ifba.inf011.template_method.hooks.TwitterPublishingWorkflow;

// Client
public class Main {

    public static void main(String[] args) {

        SocialPost facebookPost = new SocialPost("facebook", "Olá pelo Facebook!");
        SocialPost twitterPost = new SocialPost("twitter", "Olá pelo Twitter!");

        System.out.println("=== Implementação 1: Template Method canônico ===");

        Network facebook = new FacebookNetwork("anderson", "12345");
        facebook.post(facebookPost);

        System.out.println();

        Network twitter = new TwitterNetwork("anderson", "12345");
        twitter.post(twitterPost);

        System.out.println("\n=== Implementação 2: Template Method com hooks ===");

        PublishingWorkflow facebookWorkflow = new FacebookPublishingWorkflow();
        facebookWorkflow.publish(facebookPost);

        System.out.println();

        PublishingWorkflow twitterWorkflow = new TwitterPublishingWorkflow();
        twitterWorkflow.publish(twitterPost);

        System.out.println("\n=== Implementação 3: Template Method com Factory Method ===");

        SocialPublisherApplication facebookApp =
                new FacebookPublisherApplication("anderson", "12345");

        facebookApp.publish(facebookPost);

        System.out.println();

        SocialPublisherApplication twitterApp =
                new TwitterPublisherApplication("anderson", "12345");

        twitterApp.publish(twitterPost);

        System.out.println("\n=== Implementação 4: Template Method combinado com Chain of Responsibility ===");

        PostHandler chain = new FacebookPostHandler(
                new TwitterPostHandler()
        );

        chain.publish(twitterPost);
        System.out.println();
        chain.publish(facebookPost);
        System.out.println();
        chain.publish(new SocialPost("linkedin", "Post sem handler disponível."));
    }
}
