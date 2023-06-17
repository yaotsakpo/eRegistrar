package eRegistrar.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Block {

    @Id
    @GeneratedValue

    private int blockID;

    private String blockName;

    private LocalDate startingDate;

    private LocalDate endingDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Registration> registrationList;

    public Block() {
    }

    public Block( String blockName, LocalDate startingDate, LocalDate endingDate) {
        this.blockName = blockName;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }


    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public List<Registration> getRegistrationList() {
        return registrationList;
    }

    public void setRegistrationList(List<Registration> registrationList) {
        this.registrationList = registrationList;
    }
}
