package dataLaag;

import javafx.scene.image.Image;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import domein.Aankoper;
import domein.Barcode;
import domein.BedrijfsBestelling;
import domein.BedrijfsDoos;
import domein.BedrijfsKlant;
import domein.BedrijfsProduct;
import domein.BedrijfsProductBestelling;
import domein.Doos;
import domein.ProductBestelling;
import domein.Status;
import domein.StatusEntity;
import domein.Type;
import domein.BedrijfsTransportdienst;
import domein.BedrijfsUser;
import util.BCrypt;
import util.JPAUtil;

public class DataClass {
	public static EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
	public static EntityManager em = emf.createEntityManager();
	private List<String> emailContactPersonen = new ArrayList<>();
	private List<String> telefoonNrContactPersonen = new ArrayList<>();

	public void aanmaakData() {
		List<Aankoper> aankoperLijst1 = new ArrayList<Aankoper>();
		List<Aankoper> aankoperLijst2 = new ArrayList<Aankoper>();
		List<Aankoper> aankoperLijst3 = new ArrayList<Aankoper>();
		List<Aankoper> aankoperLijst4 = new ArrayList<Aankoper>();
		Aankoper a1 = new Aankoper("a49f400e-b0f6-4aaf-8a56-cf78fe2ad6be","Pol", "pol@mail.com", BCrypt.hashpw("Mclaren123", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null,"8742136590", 1);
		Aankoper a2 = new Aankoper("141d7f71-622a-4acc-abad-8b301955472d","Jos", "jos@mail.com", BCrypt.hashpw("Mclaren123", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null,"2468013579", 2);
		Aankoper a3 = new Aankoper("906c6853-0353-45b5-84bf-4261a2a19e20","kevin", "kevin@mail.com", BCrypt.hashpw("Mclaren123", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null,"5907641823", 1);
		Aankoper a4 = new Aankoper("46908591-3160-4e60-bcd9-a2623fdf9a29","piet", "piet@mail.com", BCrypt.hashpw("Mclaren123", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null, "9876543210", 2);
		Aankoper a5 = new Aankoper("22323232-4545-6e67-efg8-c34343434343","Jan", "jan@mail.com", BCrypt.hashpw("Passw0rd!", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null,"5647382910", 1);
		Aankoper a6 = new Aankoper("77777777-8888-9999-abcd-1234567890ab","Sarah", "sarah@mail.com", BCrypt.hashpw("Pa$$word123", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null,"3847261590", 2);
		Aankoper a7 = new Aankoper("a1b2c3d4-e5f6-g7h8-i9j0-k1l2m3n4o5p6","John", "john@mail.com", BCrypt.hashpw("Qwerty123!", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null,"7319054682", 1);
		Aankoper a8 = new Aankoper("xy12z34a-56b7-89cd-efg0-hi12j3kl4m5n","Samantha", "samantha@mail.com", BCrypt.hashpw("Password123$", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null,"1568904723", 2);
		Aankoper a9 = new Aankoper("pqr5s6t7-u8v9-wx1y2z3a-4b5c6d7e8f9g","Michael", "michael@mail.com", BCrypt.hashpw("Summer2023", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null,"8021354976", 1);
		Aankoper a10 = new Aankoper("11111111-2222-3333-4444-555555555555","Anna", "anna@mail.com", BCrypt.hashpw("Winter2022!", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null, "6452901837", 2);

		aankoperLijst1.add(a1);
		aankoperLijst1.add(a2);
		aankoperLijst1.add(a3);
		aankoperLijst1.add(a4);
		aankoperLijst2.add(a5);
		aankoperLijst2.add(a6);
		aankoperLijst3.add(a10);
		aankoperLijst4.add(a7);
		aankoperLijst4.add(a8);
		aankoperLijst4.add(a9);

		StatusEntity se3 = new StatusEntity("InBehandeling");
		StatusEntity se1 = new StatusEntity("Verwerkt");
		StatusEntity se2 = new StatusEntity("Geplaatst");
		StatusEntity se4 = new StatusEntity("Geleverd");
		StatusEntity se5 = new StatusEntity("Geannuleerd");
		
		BedrijfsUser u1 = new BedrijfsUser("Admin1", "Lander", "De Kesel", "Mclaren123", "Imkerstraat 8", "0479583092",
				"0951445297", "landerdekesel@gmail.com", "Admin");
		BedrijfsUser u2 = new BedrijfsUser("Magazijnier1", "Casper", "De Bock", "Mclaren123", "Straat 0", "0446555555",
				"0971448997", "casperdebock@gmail.com", "Magazijnier");
		Barcode br1 = new Barcode(4, true, "VOL");
		Barcode br2 = new Barcode(5, false, "MAN");
		Barcode br3 = new Barcode(6, true, "MER");
		Barcode br4 = new Barcode(3, false, "REN");
		Barcode br5 = new Barcode(6, false, "SCA");
		InputStream streamBlis = getClass().getResourceAsStream("../resources/blissfulbites.png");
		Image blisLogo = new Image(streamBlis);
		InputStream streamHappy = getClass().getResourceAsStream("../resources/happyTails.png");
		Image happyLogo = new Image(streamHappy);
		BedrijfsKlant k1 = new BedrijfsKlant("Blissful Bites", "BlissfulBites@gmail.com", "BlissfulBites",
				"0487679271", aankoperLijst1, "Stratem 5 9000 Gent", blisLogo);
		BedrijfsKlant k2 = new BedrijfsKlant("Happy Tails", "HappyTails@gmail.com", "HappyTails", "0487679271",
				aankoperLijst2, "Poortstraat 7 2000 Antwerpen", happyLogo);
		emailContactPersonen.add("contact1@mail.com");
		emailContactPersonen.add("contact2@mail.com");
		emailContactPersonen.add("contact3@mail.com");
		telefoonNrContactPersonen.add("0475124798");
		telefoonNrContactPersonen.add("0456324798");
		telefoonNrContactPersonen.add("0497458738");
		BedrijfsTransportdienst td1 = new BedrijfsTransportdienst("VOLVO Camion", "Stationstraat 1",
				emailContactPersonen, telefoonNrContactPersonen, Status.Actief, br1);
		BedrijfsTransportdienst td2 = new BedrijfsTransportdienst("MAN Camion", "Stationstraat 2", emailContactPersonen,
				telefoonNrContactPersonen, Status.Actief, br2);
		BedrijfsTransportdienst td3 = new BedrijfsTransportdienst("MERCEDES Camion", "Stationstraat 3",
				emailContactPersonen, telefoonNrContactPersonen, Status.Actief, br3);
		BedrijfsTransportdienst td4 = new BedrijfsTransportdienst("RENAULT Camion", "Stationstraat 4",
				emailContactPersonen, telefoonNrContactPersonen, Status.Inactief, br4);
		BedrijfsTransportdienst td5 = new BedrijfsTransportdienst("SCANIA Camion", "Stationstraat 5",
				emailContactPersonen, telefoonNrContactPersonen, Status.Inactief, br5);
		BedrijfsDoos d3 = new BedrijfsDoos("Kartonnen Doos S", Type.Custom, "30,5x21x4,8cm", 0.68, Status.Actief);
		BedrijfsDoos d2 = new BedrijfsDoos("Kartonnen Doos M", Type.Standaard, "40,5x36x5,8cm", 0.90, Status.Inactief);
		BedrijfsDoos d1 = new BedrijfsDoos("Kartonnen Doos L", Type.Standaard, "48,5x40x6,8cm", 1.00, Status.Actief);
		BedrijfsBestelling b1 = new BedrijfsBestelling(k1, LocalDateTime.now(), "Stationstraat 10 9880 Aalter", Status.Verwerkt, d2,
				td1, "VOL-1234-4");
		BedrijfsBestelling b2 = new BedrijfsBestelling(k2, LocalDateTime.now(), "Stationstraat 11 9880 Aalter", Status.Verwerkt, d1,
				td2, "MAN-4A51B-2");
		BedrijfsBestelling b3 = new BedrijfsBestelling(k1, LocalDateTime.now(), "Stationstraat 12 9880 Aalter", Status.Verwerkt, d3,
				td3, "MER-123456-1");
		BedrijfsBestelling b4 = new BedrijfsBestelling(k1, LocalDateTime.now(), "Stationstraat 13 9880 Aalter", Status.Geplaatst, d3,
				null, null);
		BedrijfsBestelling b5 = new BedrijfsBestelling(k2, LocalDateTime.now(), "Stationstraat 14 9880 Aalter", Status.Verwerkt, d3,
				td3, "MER-789012-5");
		BedrijfsBestelling b6 = new BedrijfsBestelling(k1, LocalDateTime.now(), "Stationstraat 15 9880 Aalter", Status.Geplaatst, d3,
				null, null);
		
	    BedrijfsProduct p1 = new BedrijfsProduct("Kamerplant Leep","https://www.freepnglogos.com/uploads/plant-png/plant-png-transparent-plant-images-pluspng-26.png", "Dit is een groene leep kamerplant", 20.48, 25, "2 dagen", 3);
        BedrijfsProduct p2 = new BedrijfsProduct("Metalen kruk", "https://www.gewoonstijl.nl/media/catalog/product/d/s/dsc00266_bewerkt_440b.png", "Dit is een metalen kruk", 12.99, 50, "7 dagen", 1);
        BedrijfsProduct p3 = new BedrijfsProduct("Witte Stoel", "https://www.freeiconspng.com/uploads/white-chair-png-31.png", "Dit is een witte stoel van 35cm bij 90cm", 18.99, 100, "3 dagen", 1);
        BedrijfsProduct p4 = new BedrijfsProduct("Zwarte bank", "https://crm.eland.be/storage/media/8668/604B-Kubide-Bank-Links-200-Zwart.png", "Dit is een zwarte bank van 1 meter bij 90cm", 9.99, 200, "1 dag", 4);
        BedrijfsProduct p5 = new BedrijfsProduct("Glazen salon tafel", "https://www.ygo.be/thumbnail/6e/d3/0e/1674225807/63caa88f04295-29c85a53e054d2eb147fec03b95f2747e0112d60_Y15150004505__media_packshots__marco_1__e_commerce_channel_1920x1920.png", "Dit is een dubbele glazen salon tafel met zwart metaal", 39.99, 50, "2 dagen", 2);
        BedrijfsProduct p6 = new BedrijfsProduct("Houten eettafel", "https://static.vecteezy.com/system/resources/previews/019/922/490/non_2x/3d-wooden-table-png.png", "Een houten eet tafel gemaakt uit eiken hout", 29.99, 75, "3 dagen", 2);
        BedrijfsProduct p7 = new BedrijfsProduct("Houten stoel", "https://www.gewoonstijl.nl/media/catalog/product/a/c/act.30407_0_lisomme_jade_houten_eetkamerstoel_naturel_8f45.png", "Een houten stoel gemaakt van eikenhoud", 19.99, 100, "1 dag", 1);
        BedrijfsProduct p8 = new BedrijfsProduct("Stoffen poef", "https://www.marie-marie.com/media/0d/ee/10/1644938463/0b270bb791a144428fc3d4d569f43902f252F9252F3252Fa252Ff93aa0e73ee55a81ccce2a2b0f7c0ae931cfbdd5_poef_coomba_21.png", "Stoffen poef gemaakt om te rusten", 14.99, 150, "2 dagen", 4);
        BedrijfsProduct p9 = new BedrijfsProduct("Leren zetel", "https://www.femat.be/ftp/ITEMS/small/1094.PNG", "Leren zetel gemaakt uit italiaans leer", 24.99, 50, "7 dagen", 4);
        BedrijfsProduct p10 = new BedrijfsProduct("Vintage eetkamerstoel", "https://medias.maisonsdumonde.com/image/upload/q_auto,f_auto/w_2000/img/bistrostoel-van-rotan-en-eikenhout-1000-7-36-111884_10.jpg", "Vintage eetkamerstoel met armleuningen, gemaakt van gerecycled hout", 27.99, 80, "3 dagen", 1);
        BedrijfsProduct p11 = new BedrijfsProduct("Roze fluwelen kruk", "https://medias.maisonsdumonde.com/image/upload/q_auto,f_auto/w_2000/img/poef-met-roze-en-goudkleurig-velourseffect-1000-4-20-194688_1.jpg", "Roze fluwelen kruk met gouden metalen poten", 16.99, 60, "2 dagen", 1);
        BedrijfsProduct p12 = new BedrijfsProduct("Lamp met houten poot", "https://medias.maisonsdumonde.com/image/upload/q_auto,f_auto/w_2000/img/kastanjebruine-driepotige-lamp-met-ecru-lampenkap-1000-10-7-211116_1.jpg", "Lamp met gouden kap en houten poot", 32.99, 40, "4 dagen", 7);
        BedrijfsProduct p13 = new BedrijfsProduct("Kunstplant palmboom", "https://medias.maisonsdumonde.com/image/upload/q_auto,f_auto/w_2000/img/groene-en-bruine-kunstpalmboom-1000-6-29-232991_1.jpg", "Kunstplant van een palmboom met een natuurlijke uitstraling", 18.99, 100, "7 dagen", 3);
        BedrijfsProduct p14 = new BedrijfsProduct("Lounge stoel", "https://medias.maisonsdumonde.com/image/upload/q_auto,f_auto/w_2000/img/beige-fauteuil-met-vergulde-metalen-poten-1000-10-10-216080_1.jpg", "Comfortabele lounge stoel met bruin leer en een houten onderstel", 64.99, 30, "7 dagen", 4);
        BedrijfsProduct p15 = new BedrijfsProduct("Gouden bijzettafel", "https://medias.maisonsdumonde.com/image/upload/q_auto,f_auto/w_2000/img/rond-bijzettafeltje-van-mat-goudkleurig-metaal-1000-11-30-191295_1.jpg", "Gouden bijzettafel met een marmeren blad", 49.99, 20, "2 dagen", 2);
        BedrijfsProduct p16 = new BedrijfsProduct("Industriële barkruk", "https://media.s-bol.com/7DVGo6EB8PvB/lOwxAQ7/550x551.jpg", "Industriële barkruk met een houten zitting en een metalen onderstel", 21.99, 70, "7 dagen", 1);
        BedrijfsProduct p17 = new BedrijfsProduct("Zwarte bureaustoel", "https://www.ygo.be/thumbnail/0c/e2/9c/1680871030/64300e756f6f2-718f1ea28aa4406f0fabe809e6307d8ba1e458e7_Y15250008105__media_packshots__plats_1__e_commerce_channel_1920x1920.png", "Dit is een comfortabele zwarte bureaustoel met een verstelbare rugleuning", 34.99, 75, "2 dagen", 1);
        BedrijfsProduct p18 = new BedrijfsProduct("Witte plantenbak", "https://action.com/hostedassets/CMSArticleImages/99/69/2574080_8714075045607-111_02.png", "Dit is een witte plantenbak van keramiek, geschikt voor binnen en buiten", 22.99, 100, "7 dagen", 3);
        BedrijfsProduct p19 = new BedrijfsProduct("Gele fauteuil", "https://cdn.sofacompany.com/media/catalog/product/6/4/64195ce11a1db.jpg?width=800&height=800&store=be&image-type=small_image", "Dit is een gele fauteuil met een hoge rugleuning, perfect voor ontspanning", 299.99, 10, "2 dagen", 4);
        BedrijfsProduct p20 = new BedrijfsProduct("Zwarte metalen kast", "https://production-beheer.povag.nl/storage/configurationoptions-media/14647/responsive-images/media-libraryiE7Kj0___webp_576_576.webp", "Dit is een zwarte metalen kast met twee deuren en vier laden, ideaal voor opbergruimte", 169.99, 25, "7 dagen", 5);
        BedrijfsProduct p21 = new BedrijfsProduct("Houten opbergkist", "https://nl.vidaxl.be/dw/image/v2/BFNS_PRD/on/demandware.static/-/Sites-vidaxl-catalog-master-sku/default/dwb315ceea/hi-res/436/6356/4205/289641/image_1_289641.jpg?sw=400", "Dit is een houten opbergkist met een deksel en handvatten aan beide zijden", 226.99, 100, "3 dagen", 5);
        BedrijfsProduct p22 = new BedrijfsProduct("Zwarte bureautafel", "https://production-beheer.povag.nl/storage/products-media/17969/responsive-images/media-libraryBycJPa___webp_576_576.webp", "Dit is een zwarte bureautafel met een lade en opbergruimte aan beide zijden", 88.00, 50, "7 dagen", 2);
        BedrijfsProduct p23 = new BedrijfsProduct("Houten plantenrek", "https://action.com/hostedassets/CMSArticleImages/51/88/3004800_8717582167268-110_01.png", "Dit is een houten plantenrek met drie planken, perfect om je planten op te zetten", 29.99, 75, "2 dagen", 3);
        BedrijfsProduct p24 = new BedrijfsProduct("Laptop stand", "https://image.coolblue.be/max/500x500/products/1608594", "Ergonomische laptop stand om nekklachten te voorkomen", 14.99, 50, "2 dagen", 7);
        BedrijfsProduct p25 = new BedrijfsProduct("Zwarte kast", "https://nl.vidaxl.be/dw/image/v2/BFNS_PRD/on/demandware.static/-/Sites-vidaxl-catalog-master-sku/default/dwc7b17730/hi-res/436/464/465/800154/image_2_800154.jpg?sw=400", "Grote zwarte kast met meerdere opbergvakken", 39.99, 25, "1 dag", 5);
        BedrijfsProduct p26 = new BedrijfsProduct("Witte boekenkast", "https://medias.maisonsdumonde.com/image/upload/q_auto,f_auto/w_2000/img/witte-asymmetrisch-boekenkast-1000-10-1-150307_0.jpg", "Moderne witte boekenkast met vijf planken", 49.99, 20, "2 dagen", 5);
        BedrijfsProduct p27 = new BedrijfsProduct("Houten spiegel", "https://medias.maisonsdumonde.com/image/upload/q_auto,f_auto/w_2000/img/lichte-spiegel-uit-paulowniahout-105-x-181-cm-1000-1-1-211192_1.jpg", "Grote houten spiegel voor in de slaapkamer", 24.99, 15, "1 dag", 6);
        BedrijfsProduct p28 = new BedrijfsProduct("Kleurrijke poster", "https://cdn1.home24.net/images/media/catalog/product/455x455/png/-/1/-1000291929-210923-11292500756-IMAGE-P000000001000291929.webp", "Moderne muurdecoratie met verschillende kleuren", 19.99, 50, "3 dagen", 6);
        BedrijfsProduct p29 = new BedrijfsProduct("Bureaustoel", "https://image.coolblue.be/max/500x500/products/1872342", "Comfortabele bureaustoel met verstelbare armleuningen", 79.99, 10, "1 dag", 1);
        BedrijfsProduct p30 = new BedrijfsProduct("Opbergboxen", "https://raja.scene7.com/is/image/Raja/products/voordelige-opbergbox-39-x-30-x-36-cm_QLINE52.jpg?template=withpicto410&$image=M_QLINE52_S_FR&$picto=NoPicto&hei=410&wid=410&fmt=jpg&qlt=85,0&resMode=sharp2&op_usm=1.75,0.3,2,0", "Set van drie opbergboxen in verschillende maten", 14.99, 30, "2 dagen", 5);
        BedrijfsProduct p31 = new BedrijfsProduct("Plantenbak", "https://dc94gqcjteixu.cloudfront.net/14159/original/11184.jpg?browser-cache=2023-04-28_15:14:15", "Moderne plantenbak voor binnen of buiten", 34.99, 15, "3 dagen", 3);
        
        
        BedrijfsProductBestelling pb1 = new BedrijfsProductBestelling(p1, b3, 6);
		BedrijfsProductBestelling pb2 = new BedrijfsProductBestelling(p2, b3, 15);
		BedrijfsProductBestelling pb3 = new BedrijfsProductBestelling(p1, b1, 20);
		BedrijfsProductBestelling pb4 = new BedrijfsProductBestelling(p7, b1, 1);
		BedrijfsProductBestelling pb5 = new BedrijfsProductBestelling(p4, b6, 3);
		BedrijfsProductBestelling pb6 = new BedrijfsProductBestelling(p8, b2, 24);
		BedrijfsProductBestelling pb7 = new BedrijfsProductBestelling(p9, b5, 24);
		BedrijfsProductBestelling pb8 = new BedrijfsProductBestelling(p5, b3, 24);
		BedrijfsProductBestelling pb9 = new BedrijfsProductBestelling(p1, b4, 24);

		em.getTransaction().begin();
		em.persist(a1);
		em.persist(a2);
		em.persist(a3);
		em.persist(a4);
		em.persist(a5);
		em.persist(a6);
		em.persist(a7);
		em.persist(a8);
		em.persist(a9);
		em.persist(a10);
		em.persist(k1);
		em.persist(d1);
		em.persist(d2);
		em.persist(se3);
		em.persist(se1);
		em.persist(se2);
		em.persist(se4);
		em.persist(se5);
		em.persist(d3);
		em.persist(b1);
		em.persist(b2);
		em.persist(b3);
		em.persist(b4);
		em.persist(b5);
		em.persist(b6);
		em.persist(br1);
		em.persist(br2);
		em.persist(br3);
		em.persist(br4);
		em.persist(br5);
		em.persist(u1);
		em.persist(u2);
		em.persist(td1);
		em.persist(td2);
		em.persist(td3);
		em.persist(td4);
		em.persist(td5);
		em.persist(k1);
		em.persist(k2);
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.persist(p4);
		em.persist(p5);
		em.persist(p6);
		em.persist(p7);
		em.persist(p8);
		em.persist(p9);
		em.persist(p10);
		em.persist(p11);
		em.persist(p12);
		em.persist(p13);
		em.persist(p14);
		em.persist(p15);
		em.persist(p16);
		em.persist(p17);
		em.persist(p18);
		em.persist(p19);
		em.persist(p20);
		em.persist(p21);
		em.persist(p22);
		em.persist(p23);
		em.persist(p24);
		em.persist(p25);
		em.persist(p26);
		em.persist(p27);
		em.persist(p28);
		em.persist(p29);
		em.persist(p30);
		em.persist(p31);
		
		em.persist(pb1);
		em.persist(pb2);
		em.persist(pb3);
		em.persist(pb4);
		em.persist(pb5);
		em.persist(pb6);
		em.persist(pb7);
		em.persist(pb8);
		em.persist(pb9);
		em.getTransaction().commit();
	}
}