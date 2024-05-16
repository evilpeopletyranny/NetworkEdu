package vst.science.ontologies.utils.write;

public enum ONT_WRITE_FORMAT {
    RDF_XML_ABBREV("RDF/XML-ABBREV"),
    RDF_XML("RDF/XML");

    public final String value;

    ONT_WRITE_FORMAT(String value) {
        this.value = value;
    }
}
