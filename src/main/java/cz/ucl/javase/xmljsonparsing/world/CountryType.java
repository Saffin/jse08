
package cz.ucl.javase.xmljsonparsing.world;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for countryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="countryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="countryNames" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="city" type="{}cityType"/>
 *         &lt;element name="ethnicgroups" type="{}ethnicgroupsType"/>
 *         &lt;element name="religions" type="{}religionsType"/>
 *         &lt;element name="encompassed" type="{}encompassedType"/>
 *         &lt;element name="border" type="{}borderType"/>
 *         &lt;element name="province" type="{}provinceType"/>
 *         &lt;element name="languages" type="{}languagesType"/>
 *       &lt;/choice>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="countryName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="capital" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="population" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="datacode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="total_area" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="population_growth" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="infant_mortality" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="gdp_agri" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="gdp_total" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="inflation" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="indep_date" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="government" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="car_code" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="gdp_ind" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="gdp_serv" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "countryType", propOrder = {
    "countryNamesOrCityOrEthnicgroups"
})
public class CountryType {

    @XmlElements({
        @XmlElement(name = "countryNames", type = String.class),
        @XmlElement(name = "city", type = CityType.class),
        @XmlElement(name = "ethnicgroups", type = EthnicgroupsType.class),
        @XmlElement(name = "religions", type = ReligionsType.class),
        @XmlElement(name = "encompassed", type = EncompassedType.class),
        @XmlElement(name = "border", type = BorderType.class),
        @XmlElement(name = "province", type = ProvinceType.class),
        @XmlElement(name = "languages", type = LanguagesType.class)
    })
    protected List<Object> countryNamesOrCityOrEthnicgroups;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "countryName")
    protected String countryName;
    @XmlAttribute(name = "capital")
    protected String capital;
    @XmlAttribute(name = "population")
    protected String population;
    @XmlAttribute(name = "datacode")
    protected String datacode;
    @XmlAttribute(name = "total_area")
    protected Float totalArea;
    @XmlAttribute(name = "population_growth")
    protected Float populationGrowth;
    @XmlAttribute(name = "infant_mortality")
    protected Float infantMortality;
    @XmlAttribute(name = "gdp_agri")
    protected Float gdpAgri;
    @XmlAttribute(name = "gdp_total")
    protected Float gdpTotal;
    @XmlAttribute(name = "inflation")
    protected Float inflation;
    @XmlAttribute(name = "indep_date")
    protected String indepDate;
    @XmlAttribute(name = "government")
    protected String government;
    @XmlAttribute(name = "car_code")
    protected String carCode;
    @XmlAttribute(name = "gdp_ind")
    protected Float gdpInd;
    @XmlAttribute(name = "gdp_serv")
    protected Float gdpServ;

    /**
     * Gets the value of the countryNamesOrCityOrEthnicgroups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the countryNamesOrCityOrEthnicgroups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCountryNamesOrCityOrEthnicgroups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * {@link CityType }
     * {@link EthnicgroupsType }
     * {@link ReligionsType }
     * {@link EncompassedType }
     * {@link BorderType }
     * {@link ProvinceType }
     * {@link LanguagesType }
     * 
     * 
     */
    public List<Object> getCountryNamesOrCityOrEthnicgroups() {
        if (countryNamesOrCityOrEthnicgroups == null) {
            countryNamesOrCityOrEthnicgroups = new ArrayList<Object>();
        }
        return this.countryNamesOrCityOrEthnicgroups;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the countryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the value of the countryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryName(String value) {
        this.countryName = value;
    }

    /**
     * Gets the value of the capital property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCapital() {
        return capital;
    }

    /**
     * Sets the value of the capital property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCapital(String value) {
        this.capital = value;
    }

    /**
     * Gets the value of the population property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopulation() {
        return population;
    }

    /**
     * Sets the value of the population property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopulation(String value) {
        this.population = value;
    }

    /**
     * Gets the value of the datacode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatacode() {
        return datacode;
    }

    /**
     * Sets the value of the datacode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatacode(String value) {
        this.datacode = value;
    }

    /**
     * Gets the value of the totalArea property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getTotalArea() {
        return totalArea;
    }

    /**
     * Sets the value of the totalArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setTotalArea(Float value) {
        this.totalArea = value;
    }

    /**
     * Gets the value of the populationGrowth property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getPopulationGrowth() {
        return populationGrowth;
    }

    /**
     * Sets the value of the populationGrowth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setPopulationGrowth(Float value) {
        this.populationGrowth = value;
    }

    /**
     * Gets the value of the infantMortality property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getInfantMortality() {
        return infantMortality;
    }

    /**
     * Sets the value of the infantMortality property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setInfantMortality(Float value) {
        this.infantMortality = value;
    }

    /**
     * Gets the value of the gdpAgri property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getGdpAgri() {
        return gdpAgri;
    }

    /**
     * Sets the value of the gdpAgri property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setGdpAgri(Float value) {
        this.gdpAgri = value;
    }

    /**
     * Gets the value of the gdpTotal property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getGdpTotal() {
        return gdpTotal;
    }

    /**
     * Sets the value of the gdpTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setGdpTotal(Float value) {
        this.gdpTotal = value;
    }

    /**
     * Gets the value of the inflation property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getInflation() {
        return inflation;
    }

    /**
     * Sets the value of the inflation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setInflation(Float value) {
        this.inflation = value;
    }

    /**
     * Gets the value of the indepDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndepDate() {
        return indepDate;
    }

    /**
     * Sets the value of the indepDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndepDate(String value) {
        this.indepDate = value;
    }

    /**
     * Gets the value of the government property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGovernment() {
        return government;
    }

    /**
     * Sets the value of the government property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGovernment(String value) {
        this.government = value;
    }

    /**
     * Gets the value of the carCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarCode() {
        return carCode;
    }

    /**
     * Sets the value of the carCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarCode(String value) {
        this.carCode = value;
    }

    /**
     * Gets the value of the gdpInd property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getGdpInd() {
        return gdpInd;
    }

    /**
     * Sets the value of the gdpInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setGdpInd(Float value) {
        this.gdpInd = value;
    }

    /**
     * Gets the value of the gdpServ property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getGdpServ() {
        return gdpServ;
    }

    /**
     * Sets the value of the gdpServ property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setGdpServ(Float value) {
        this.gdpServ = value;
    }

}
