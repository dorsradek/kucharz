package pl.dors.radoslaw.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.dors.radoslaw.app.kucharz.model.MiaraProduktu;
import pl.dors.radoslaw.app.kucharz.model.Produkt;
import pl.dors.radoslaw.app.kucharz.model.RodzajProduktu;
import pl.dors.radoslaw.app.kucharz.service.MiaraProduktuService;
import pl.dors.radoslaw.app.kucharz.service.ProduktService;
import pl.dors.radoslaw.app.kucharz.service.RodzajProduktuService;

@Controller
@RequestMapping("/product")
public class ProduktController {

	@Autowired
	private ProduktService produktService;

	@Autowired
	private MiaraProduktuService miaraProduktuService;

	@Autowired
	private RodzajProduktuService rodzajProduktuService;

	@RequestMapping("/productlist.json")
	public @ResponseBody List<Produkt> getProductList() {
		return produktService.findAll();
	}

	@RequestMapping("/miary")
	public @ResponseBody List<MiaraProduktu> getMiaryList() {
		return miaraProduktuService.findAll();
	}

	@RequestMapping("/rodzaje")
	public @ResponseBody List<RodzajProduktu> getRodzajeList() {
		return rodzajProduktuService.findAll();
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public @ResponseBody void addProduct(@RequestBody Produkt product) {
		produktService.save(product);
	}

	@RequestMapping(value = "/removeProduct", method = RequestMethod.POST)
	public @ResponseBody void removeProduct(@RequestBody Produkt product) {
		System.out.println("asd");
		produktService.delete(product);
	}

	@RequestMapping(value = "/editProduct", method = RequestMethod.POST)
	public @ResponseBody void editProduct(@RequestBody Produkt product) {
		produktService.save(product);
	}

	@RequestMapping(value = "/find/{productId}", method = RequestMethod.GET)
	public @ResponseBody Produkt findProduct(@PathVariable String productId) {
		return produktService.findById(Long.valueOf(productId));
	}

	@RequestMapping(value = "/findMiara/{productId}", method = RequestMethod.GET)
	public @ResponseBody MiaraProduktu findMiara(@PathVariable String productId) {
		MiaraProduktu result = null;
		List<MiaraProduktu> miaryProduktow = miaraProduktuService.findAll();
		for (MiaraProduktu miaraProduktu : miaryProduktow) {
			for (Produkt produkt : miaraProduktu.getProdukty()) {
				if (produkt.getPrdId().equals(Long.valueOf(productId))) {
					result = miaraProduktu;
					break;
				}
			}
			if (result != null) {
				break;
			}
		}
		return result;
	}

	@RequestMapping(value = "/findRodzaj/{productId}", method = RequestMethod.GET)
	public @ResponseBody RodzajProduktu findRodzaj(@PathVariable String productId) {
		RodzajProduktu result = null;
		List<RodzajProduktu> rodzajeProduktow = rodzajProduktuService.findAll();
		for (RodzajProduktu rodzajProduktu : rodzajeProduktow) {
			for (Produkt produkt : rodzajProduktu.getProdukty()) {
				if (produkt.getPrdId().equals(Long.valueOf(productId))) {
					result = rodzajProduktu;
					break;
				}
			}
			if (result != null) {
				break;
			}
		}
		return result;
	}

}
