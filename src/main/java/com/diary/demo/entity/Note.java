package com.diary.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
/*CREATE TABLE `db_for_diary`.`notes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `note` VARCHAR(100) NOT NULL,
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `done` BIT(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`));*/

@Entity
@Table(name = "notes", schema = "db_for_diary", catalog = "")
@NoArgsConstructor
@Data
public class Note{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "note")
//    @Size(min = 2)
    private String note;
    @Column(name = "date")
   /* @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "EEE, d MMM yyyy HH:mm")*/
    private Date date;
    @Column(name = "done")
    private boolean done;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Note(String note, User user){
        this.note = note;
        this.author = user;
        this.date = new Date();
        this.done = false;
    }
    public Note(String note, Date date){
        this.note = note;
        this.date = date;
        this.done = false;
    }
}