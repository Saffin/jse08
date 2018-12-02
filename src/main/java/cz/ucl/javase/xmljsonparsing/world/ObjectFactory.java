
package cz.ucl.javase.xmljsonparsing.world;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cz.ucl.javase.xmljsonparsing.world package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _World_QNAME = new QName("", "world");
    private final static QName _OrganizationTypeMembers_QNAME = new QName("", "members");
    private final static QName _SeaTypeLocated_QNAME = new QName("", "located");
    private final static QName _ProvinceTypeCity_QNAME = new QName("", "city");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.ucl.javase.xmljsonparsing.world
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WorldType }
     * 
     */
    public WorldType createWorldType() {
        return new WorldType();
    }

    /**
     * Create an instance of {@link CityType }
     * 
     */
    public CityType createCityType() {
        return new CityType();
    }

    /**
     * Create an instance of {@link BorderType }
     * 
     */
    public BorderType createBorderType() {
        return new BorderType();
    }

    /**
     * Create an instance of {@link DesertsType }
     * 
     */
    public DesertsType createDesertsType() {
        return new DesertsType();
    }

    /**
     * Create an instance of {@link MountainType }
     * 
     */
    public MountainType createMountainType() {
        return new MountainType();
    }

    /**
     * Create an instance of {@link CountryType }
     * 
     */
    public CountryType createCountryType() {
        return new CountryType();
    }

    /**
     * Create an instance of {@link LanguagesType }
     * 
     */
    public LanguagesType createLanguagesType() {
        return new LanguagesType();
    }

    /**
     * Create an instance of {@link ReligionsType }
     * 
     */
    public ReligionsType createReligionsType() {
        return new ReligionsType();
    }

    /**
     * Create an instance of {@link DesertType }
     * 
     */
    public DesertType createDesertType() {
        return new DesertType();
    }

    /**
     * Create an instance of {@link IslandsType }
     * 
     */
    public IslandsType createIslandsType() {
        return new IslandsType();
    }

    /**
     * Create an instance of {@link EthnicgroupsType }
     * 
     */
    public EthnicgroupsType createEthnicgroupsType() {
        return new EthnicgroupsType();
    }

    /**
     * Create an instance of {@link EncompassedType }
     * 
     */
    public EncompassedType createEncompassedType() {
        return new EncompassedType();
    }

    /**
     * Create an instance of {@link IslandType }
     * 
     */
    public IslandType createIslandType() {
        return new IslandType();
    }

    /**
     * Create an instance of {@link RiverType }
     * 
     */
    public RiverType createRiverType() {
        return new RiverType();
    }

    /**
     * Create an instance of {@link ContinentsType }
     * 
     */
    public ContinentsType createContinentsType() {
        return new ContinentsType();
    }

    /**
     * Create an instance of {@link CountriesType }
     * 
     */
    public CountriesType createCountriesType() {
        return new CountriesType();
    }

    /**
     * Create an instance of {@link LocatedType }
     * 
     */
    public LocatedType createLocatedType() {
        return new LocatedType();
    }

    /**
     * Create an instance of {@link LakesType }
     * 
     */
    public LakesType createLakesType() {
        return new LakesType();
    }

    /**
     * Create an instance of {@link LakeType }
     * 
     */
    public LakeType createLakeType() {
        return new LakeType();
    }

    /**
     * Create an instance of {@link SeasType }
     * 
     */
    public SeasType createSeasType() {
        return new SeasType();
    }

    /**
     * Create an instance of {@link LocatedAtType }
     * 
     */
    public LocatedAtType createLocatedAtType() {
        return new LocatedAtType();
    }

    /**
     * Create an instance of {@link ToType }
     * 
     */
    public ToType createToType() {
        return new ToType();
    }

    /**
     * Create an instance of {@link ContinentType }
     * 
     */
    public ContinentType createContinentType() {
        return new ContinentType();
    }

    /**
     * Create an instance of {@link ProvinceType }
     * 
     */
    public ProvinceType createProvinceType() {
        return new ProvinceType();
    }

    /**
     * Create an instance of {@link RiversType }
     * 
     */
    public RiversType createRiversType() {
        return new RiversType();
    }

    /**
     * Create an instance of {@link SeaType }
     * 
     */
    public SeaType createSeaType() {
        return new SeaType();
    }

    /**
     * Create an instance of {@link OrganizationType }
     * 
     */
    public OrganizationType createOrganizationType() {
        return new OrganizationType();
    }

    /**
     * Create an instance of {@link OrganizationsType }
     * 
     */
    public OrganizationsType createOrganizationsType() {
        return new OrganizationsType();
    }

    /**
     * Create an instance of {@link PopulationType }
     * 
     */
    public PopulationType createPopulationType() {
        return new PopulationType();
    }

    /**
     * Create an instance of {@link MembersType }
     * 
     */
    public MembersType createMembersType() {
        return new MembersType();
    }

    /**
     * Create an instance of {@link MountainsType }
     * 
     */
    public MountainsType createMountainsType() {
        return new MountainsType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WorldType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "world")
    public JAXBElement<WorldType> createWorld(WorldType value) {
        return new JAXBElement<WorldType>(_World_QNAME, WorldType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MembersType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "members", scope = OrganizationType.class)
    public JAXBElement<MembersType> createOrganizationTypeMembers(MembersType value) {
        return new JAXBElement<MembersType>(_OrganizationTypeMembers_QNAME, MembersType.class, OrganizationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocatedType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "located", scope = SeaType.class)
    public JAXBElement<LocatedType> createSeaTypeLocated(LocatedType value) {
        return new JAXBElement<LocatedType>(_SeaTypeLocated_QNAME, LocatedType.class, SeaType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocatedType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "located", scope = IslandType.class)
    public JAXBElement<LocatedType> createIslandTypeLocated(LocatedType value) {
        return new JAXBElement<LocatedType>(_SeaTypeLocated_QNAME, LocatedType.class, IslandType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocatedType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "located", scope = DesertType.class)
    public JAXBElement<LocatedType> createDesertTypeLocated(LocatedType value) {
        return new JAXBElement<LocatedType>(_SeaTypeLocated_QNAME, LocatedType.class, DesertType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CityType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "city", scope = ProvinceType.class)
    public JAXBElement<CityType> createProvinceTypeCity(CityType value) {
        return new JAXBElement<CityType>(_ProvinceTypeCity_QNAME, CityType.class, ProvinceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocatedType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "located", scope = LakeType.class)
    public JAXBElement<LocatedType> createLakeTypeLocated(LocatedType value) {
        return new JAXBElement<LocatedType>(_SeaTypeLocated_QNAME, LocatedType.class, LakeType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocatedType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "located", scope = MountainType.class)
    public JAXBElement<LocatedType> createMountainTypeLocated(LocatedType value) {
        return new JAXBElement<LocatedType>(_SeaTypeLocated_QNAME, LocatedType.class, MountainType.class, value);
    }

}
