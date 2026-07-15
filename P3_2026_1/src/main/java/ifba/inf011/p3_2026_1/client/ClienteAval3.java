package ifba.inf011.p3_2026_1.client;
// package ifba.inf011.p3_2026_1.model;

// import java.util.ArrayList;
// import java.util.List;

// import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.CinemaTimelineBuilder;
// import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.Timeline;
// import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.TimelineBuilder;
// import ifba.inf011.p3_2026_1.model.comercial.Filme;
// import ifba.inf011.p3_2026_1.model.comercial.Pacote;
// import ifba.inf011.p3_2026_1.model.playlist.MP3;
// import ifba.inf011.p3_2026_1.model.playlist.Playlist;

// public class ClienteAval3 {

// 	public void run() {
		
// 		TimelineBuilder builder = new CinemaTimelineBuilder();
// 	    Timeline cinemaTimeline = builder.reset().addClassAdapterVideo("MainShot_4K.mov").build();
	    
// 	    List<Filme> filmes = new ArrayList<Filme>();
// 	    filmes.add(new Filme("Matrix", 20.0, cinemaTimeline));
// 	    filmes.add(new Filme("Matrix Reloaded", 25.0, cinemaTimeline));
// 	    filmes.add(new Filme("Matrix Revolutions", 15.0, cinemaTimeline));

// 	    Pacote pacote = new Pacote("Trilogia Matrix", filmes);

//         System.out.println("Preço da Super Coleção: " + pacote.getPreco());
//         System.out.println("Duração da Super Coleção: " + pacote.getDuracao());


        

        
//         Playlist playlist = new Playlist();
        
//         playlist.addItem(pacote);
//         playlist.addItem(new MP3("Son Of A Gun", 1000));
        
//         double largura =  playlist.getBandaTotal();
//         System.out.println("Consumo de Largura de Banda: " + largura);
        
//         String  xml = playlist.toXML();
//         System.out.println(xml);
//     }		

// 	public static void main(String[] args) {
// 		new ClienteAval3().run();
// 	}

	
// }
