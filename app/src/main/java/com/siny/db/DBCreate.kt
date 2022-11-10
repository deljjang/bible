package com.siny.db

import android.database.sqlite.SQLiteDatabase

/**
 * 테이블 생성 및 테이블 수정
 */
object DBCreate {
    fun onCreate(db: SQLiteDatabase) {
        // SQLiteOpenHelper 가 최초 실행 되었을 때
        var sql =
            ("create table tb_list (cd1 integer " //신구약 구분 1:구약, 2:신약
                    + ", cd2 integer " //명칭에 대한 코드 : 1:창세기, 2:출애굽기
                    + ", nm varchar(20)  " //명칭 1:창세기, 2:출애굽기
                    + ", nm2 varchar(4)  " //약칭 1:창, 2:출
                    + ", pos integer default 1 "
                    + ", db_set integer default 0 "
                    + ")")
        db.execSQL(sql)

        sql = "CREATE INDEX idx_list_01 ON tb_list(cd1, cd2)"
        db.execSQL(sql)

        sql = "CREATE INDEX idx_list_02 ON tb_list(nm)"
        db.execSQL(sql)

        sql = "CREATE INDEX idx_list_03 ON tb_list(nm2)"
        db.execSQL(sql)

        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','1','창세기','창')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','2','출애굽기','출')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','3','레위기', '레')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','4','민수기','민')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','5','신명기','신')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','6','여호수아','수')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','7','사사기','삿')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','8','룻기','룻')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','9','사무엘상','삼상')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','10','사무엘하','삼하')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','11','열왕기상','왕상')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','12','열왕기하','왕하')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','13','역대상','대상')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','14','역대하','대하')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','15','에스라','스')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','16','느헤미야','느')")

        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','17','에스더','에')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','18','욥기','욥')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','19','시편','시')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','20','잠언','잠')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','21','전도서','전')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','22','아가','아')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','23','이사야','사')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','24','예레미야','렘')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','25','예레미야애가','애')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','26','에스겔','겔')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','27','다니엘','단')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','28','호세아','호')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','29','요엘','욜')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','30','아모스','암')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','31','오바댜','옵')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','32','요나','욘')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','33','미가','미')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','34','나훔','나')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','35','하박국','합')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','36','스바냐','습')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','37','학개','학')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','38','스가랴','슥')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('1','39','말라기','말')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '01', '마태복음','마')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '02', '마가복음','막')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '03', '누가복음','눅')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '04', '요한복음','요')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '05', '사도행전','행')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '06', '로마서','롬')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '07', '고린도전서','고전')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '08', '고린도후서','고후')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '09', '갈라디아서','갈')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '10', '에베소서','엡')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '11', '빌립보서','빌')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '12', '골로새서','골')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '13', '데살로니가전서','살전')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '14', '데살로니가후서','살후')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '15', '디모데전서','딤전')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '16', '디모데후서','딤후')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '17', '디도서','딛')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '18', '빌레몬서','몬')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '19', '히브리서','히')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '20', '야고보서','약')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '21', '베드로전서','벧전')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '22', '베드로후서','벧후')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '23', '요한일서','요일')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '24', '요한이서','요이')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '25', '요한삼서','요삼')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '26', '유다서','유')")
        db.execSQL("insert into tb_list (cd1,cd2,nm,nm2) values('2', '27', '요한계시록','계')")

        sql = ("create table tb_list2 (cd1 integer " //신구약 구분 1:구약, 2:신약
                + ", cd2 integer " //명칭에 대한 코드 : 1:창세기, 2:출애굽기
                + ", cd3 integer " //장
                + ", cd4 integer " //절
                + ", txt text " //내용
                + ", pos integer "
                + ", favorite integer default 0 "
                + " , PRIMARY KEY(cd1,cd2,cd3,cd4) "
                + ")")
        db.execSQL(sql)
    }

    fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}