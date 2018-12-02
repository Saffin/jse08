
package cz.ucl.javase.xmljsonparsing.world;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for worldType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="worldType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="continents" type="{}continentsType"/>
 *         &lt;element name="countries" type="{}countriesType"/>
 *         &lt;element name="organizations" type="{}organizationsType"/>
 *         &lt;element name="mountains" type="{}mountainsType"/>
 *         &lt;element name="deserts" type="{}desertsType"/>
 *         &lt;element name="islands" type="{}islandsType"/>
 *         &lt;element name="rivers" type="{}riversType"/>
 *         &lt;element name="seas" type="{}seasType"/>
 *         &lt;element name="lakes" type="{}lakesType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@XmlRootElement(name="world")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "worldType", propOrder = {
    "continents",
    "countries",
    "organizations",
    "mountains",
    "deserts",
    "islands",
    "rivers",
    "seas",
    "lakes"
})
public class WorldType {

    @XmlElement(required = true)
    protected ContinentsType continents;
    @XmlElement(required = true)
    protected CountriesType countries;
    @XmlElement(required = true)
    protected OrganizationsType organizations;
    @XmlElement(required = true)
    protected MountainsType mountains;
    @XmlElement(required = true)
    protected DesertsType deserts;
    @XmlElement(required = true)
    protected IslandsType islands;
    @XmlElement(required = true)
    protected RiversType rivers;
    @XmlElement(required = true)
    protected SeasType seas;
    @XmlElement(required = true)
    protected LakesType lakes;

    /**
     * Gets the value of the continents property.
     * 
     * @return
     *     possible object is
     *     {@link ContinentsType }
     *     
     */
    public ContinentsType getContinents() {
        return continents;
    }

    /**
     * Sets the value of the continents property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContinentsType }
     *     
     */
    public void setContinents(ContinentsType value) {
        this.continents = value;
    }

    /**
     * Gets the value of the countries property.
     * 
     * @return
     *     possible object is
     *     {@link CountriesType }
     *     
     */
    public CountriesType getCountries() {
        return countries;
    }

    /**
     * Sets the value of the countries property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountriesType }
     *     
     */
    public void setCountries(CountriesType value) {
        this.countries = value;
    }

    /**
     * Gets the value of the organizations property.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationsType }
     *     
     */
    public OrganizationsType getOrganizations() {
        return organizations;
    }

    /**
     * Sets the value of the organizations property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationsType }
     *     
     */
    public void setOrganizations(OrganizationsType value) {
        this.organizations = value;
    }

    /**
     * Gets the value of the mountains property.
     * 
     * @return
     *     possible object is
     *     {@link MountainsType }
     *     
     */
    public MountainsType getMountains() {
        return mountains;
    }

    /**
     * Sets the value of the mountains property.
     * 
     * @param value
     *     allowed object is
     *     {@link MountainsType }
     *     
     */
    public void setMountains(MountainsType value) {
        this.mountains = value;
    }

    /**
     * Gets the value of the deserts property.
     * 
     * @return
     *     possible object is
     *     {@link DesertsType }
     *     
     */
    public DesertsType getDeserts() {
        return deserts;
    }

    /**
     * Sets the value of the deserts property.
     * 
     * @param value
     *     allowed object is
     *     {@link DesertsType }
     *     
     */
    public void setDeserts(DesertsType value) {
        this.deserts = value;
    }

    /**
     * Gets the value of the islands property.
     * 
     * @return
     *     possible object is
     *     {@link IslandsType }
     *     
     */
    public IslandsType getIslands() {
        return islands;
    }

    /**
     * Sets the value of the islands property.
     * 
     * @param value
     *     allowed object is
     *     {@link IslandsType }
     *     
     */
    public void setIslands(IslandsType value) {
        this.islands = value;
    }

    /**
     * Gets the value of the rivers property.
     * 
     * @return
     *     possible object is
     *     {@link RiversType }
     *     
     */
    public RiversType getRivers() {
        return rivers;
    }

    /**
     * Sets the value of the rivers property.
     * 
     * @param value
     *     allowed object is
     *     {@link RiversType }
     *     
     */
    public void setRivers(RiversType value) {
        this.rivers = value;
    }

    /**
     * Gets the value of the seas property.
     * 
     * @return
     *     possible object is
     *     {@link SeasType }
     *     
     */
    public SeasType getSeas() {
        return seas;
    }

    /**
     * Sets the value of the seas property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeasType }
     *     
     */
    public void setSeas(SeasType value) {
        this.seas = value;
    }

    /**
     * Gets the value of the lakes property.
     * 
     * @return
     *     possible object is
     *     {@link LakesType }
     *     
     */
    public LakesType getLakes() {
        return lakes;
    }

    /**
     * Sets the value of the lakes property.
     * 
     * @param value
     *     allowed object is
     *     {@link LakesType }
     *     
     */
    public void setLakes(LakesType value) {
        this.lakes = value;
    }

}
