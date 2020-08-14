package com.diary.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
@Getter
@Setter
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "note")
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
    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }
}