package ifba.inf011.p3_2026_1.model;

import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.CinemaTimelineBuilder;
import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.Timeline;
import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.TimelineBuilder;
import ifba.inf011.p3_2026_1.avaliacao2.decorator.CreditsDecorator;
import ifba.inf011.p3_2026_1.avaliacao2.decorator.RenderableContent;
import ifba.inf011.p3_2026_1.avaliacao2.decorator.TrailerDecorator;

public class ClientAval2 {
	
	public void runQuestaoI() {
		TimelineBuilder builder = new CinemaTimelineBuilder();

		System.out.println("==========CRIANDO FILME COM CLASS ADAPTER============");
		
        Timeline cinemaTimeline = builder.reset()
                                         .addClassAdapterVideo("MainShot_4K.mov")
                                         .build();
        cinemaTimeline.render(0, 1000);

		System.out.println("==========CRIANDO FILME COM OBJECT ADAPTER============");
		
        cinemaTimeline = builder.reset()
                                .addObjectAdapterVideo("MainShot_4K.mov")
                                .build();
        cinemaTimeline.render(0, 1000);
        
        
        
	}
	
	
	public void runQuestaoII() {

		TimelineBuilder builder = new CinemaTimelineBuilder();

		System.out.println("==========CRIANDO FILME============");
        Timeline cinemaTimeline = builder.reset()
                .addClassAdapterVideo("MainShot_4K.mov")
                .addAudio("Soundtrack_Master.wav")
                .addSubtitle("legendas_pt.stt", "Português")
                .build();
        
		System.out.println("==========EXIBINDO FILME============");
        cinemaTimeline.render(0, 2400);
        
		System.out.println("==========EXIBINDO FILME COM TRAILER============");
        
        
        RenderableContent filme = new TrailerDecorator(cinemaTimeline, "TRAILER"); 
        filme.render(0, 2400);
        
		System.out.println("==========EXIBINDO FILME COM TRAILER E CREDITOS============");
        
        
        filme = new CreditsDecorator(filme, "CREDITOS"); 
        filme.render(0, 2400);
		
	}
	
	
	public void run() {
		System.out.println("\n=== Questão I ===");
		this.runQuestaoI();
		System.out.println("\n=== Questão II ===");
		this.runQuestaoII();
		
	}
	public static void main(String[] args) {
		new ClientAval2().run();
	}

}
