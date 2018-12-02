
package cz.ucl.javase.xmljsonparsing.world;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for riverType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="riverType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="located" type="{}locatedType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="to" type="{}toType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}short" />
 *       &lt;attribute name="riverName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "riverType", propOrder = {
    "located",
    "to"
})
public class RiverType {

    protected List<LocatedType> located;
    protected List<ToType> to;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "length")
    protected Short length;
    @XmlAttribute(name = "riverName")
    protected String riverName;

    /**
     * Gets the value of the located property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the located property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocated().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocatedType }
     * 
     * 
     */
    public List<LocatedType> getLocated() {
        if (located == null) {
            located = new ArrayList<LocatedType>();
        }
        return this.located;
    }

    /**
     * Gets the value of the to property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the to property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ToType }
     * 
     * 
     */
    public List<ToType> getTo() {
        if (to == null) {
            to = new ArrayList<ToType>();
        }
        return this.to;
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
     * Gets the value of the length property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setLength(Short value) {
        this.length = value;
    }

    /**
     * Gets the value of the riverName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRiverName() {
        return riverName;
    }

    /**
     * Sets the value of the riverName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiverName(String value) {
        this.riverName = value;
    }

}
