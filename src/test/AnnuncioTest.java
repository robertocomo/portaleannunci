package test;

import org.junit.Assert;
import org.junit.Test;

import dao.AnnuncioDao;
import model.Annuncio;
import model.CategoriaAnnuncio;
import model.StatoAnnuncio;

public class AnnuncioTest {

	@Test
	public void testAnnuncioStringStringCategoriaAnnuncioIntFloatStringInt() {

		Annuncio annuncio = new Annuncio("String titolo", "String descrizione", CategoriaAnnuncio.CREATIVITÀ, 1, 1,
				"String foto", 0);

		Assert.assertEquals("Test Annuncio.titolo", annuncio.getTitolo(), "String titolo");
		Assert.assertEquals("Test Annuncio.descrizione", annuncio.getDescrizione(), "String descrizione");
		Assert.assertEquals("Test Annuncio.categoria", annuncio.getCategoria(), CategoriaAnnuncio.CREATIVITÀ);
		Assert.assertEquals("Test Annuncio.prezzo", annuncio.getPrezzo(), 1, 0);
		Assert.assertEquals("Test Annuncio.quantità", annuncio.getQuantità(), 1);
		Assert.assertEquals("Test Annuncio.foto", annuncio.getFoto(), "String foto");
		Assert.assertEquals("Test Annuncio.stato", annuncio.getState(), StatoAnnuncio.PUBLIC);
		Assert.assertSame("Test Annuncio.creatoreID", annuncio.getCreatoreID(), 0);
	}

	@Test
	public void testSaveAnnuncioWithoutRemove() {

		Annuncio annuncio = new Annuncio("String titolo", "String descrizione", CategoriaAnnuncio.CREATIVITÀ, 1, 1,
				"String foto", 0);

		Assert.assertTrue("Test Annuncio Save with DAO", AnnuncioDao.store(annuncio));

	}

	@Test
	public void testSaveAnnuncioAndRemove() {

		Annuncio annuncio = new Annuncio("TestRemove", "TestRemove", CategoriaAnnuncio.LIBRI, 10, 10, "TestRemove", 0);

		int idAnnuncio = AnnuncioDao.storeAndGetId(annuncio);

		if (idAnnuncio == AnnuncioDao._ERROR)
			Assert.assertTrue("Test Annuncio Save with DAO", false);

		Assert.assertTrue("Test Annuncio Save with DAO", AnnuncioDao.delete(annuncio));

	}

}
