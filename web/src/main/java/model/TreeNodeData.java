package model;

import java.io.Serializable;

public class TreeNodeData implements Serializable {

    private static final long serialVersionUID = 2291949203014029830L;

    private String type;
    private Integer id;
    private String name;
    private Integer idTypeSet;

    public TreeNodeData(String type, Integer id, String name, Integer idTypeSet) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.idTypeSet = idTypeSet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdTypeSet() {
        return idTypeSet;
    }

    public void setIdTypeSet(Integer idTypeSet) {
        this.idTypeSet = idTypeSet;
    }
}
