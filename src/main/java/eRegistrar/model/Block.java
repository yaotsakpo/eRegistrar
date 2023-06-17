package eRegistrar.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blockID;

    private String blockName;

    @NotNull(message = "* Starting date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startingDate;

    @NotNull(message = "* Ending date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endingDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Registration> registrationList;

    public Block() {
    }

    public Block(int blockID, String blockName, LocalDate startingDate, LocalDate endingDate) {
        this.blockID = blockID;
        this.blockName = blockName;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public Block(String blockName, LocalDate startingDate, LocalDate endingDate) {
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

    @Override
    public String toString() {
        return "Block{" +
                "blockName='" + blockName + '\'' +
                ", startingDate=" + startingDate +
                ", endingDate=" + endingDate +
                '}';
    }
}
