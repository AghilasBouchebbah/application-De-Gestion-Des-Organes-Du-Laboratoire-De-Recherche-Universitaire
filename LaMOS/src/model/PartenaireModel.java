
package model;

public class PartenaireModel {
    public String getinserpart() {
        return "insert into partenaire(intitpart,dompart,typepart,infopart,tarifpart,dateajoutpart,logo) values( ?,?,?,?,?,?,?)";
    }
     public String getinserpartsansimage() {
        return "insert into partenaire(intitpart,dompart,typepart,infopart,tarifpart,dateajoutpart) values( ?,?,?,?,?,?)";
    }

    public String getupdatesansimage(String txt) {
        return "update partenaire Set  intitpart=?,dompart=?,typepart=?,infopart=?,tarifpart=?,dateajoutpart=? where  intitpart ='" + txt + "' ";
    }

    public String getupdateavecimage(String txt) {
        return "update partenaire Set  intitpart=?,dompart=?,typepart=?,infopart=?,tarifpart=?,dateajoutpart=?,logo=?  where  intitpart ='" + txt + "' ";
    }

    public String getselectpart() {
        return "select *  from partenaire where intitpart=?";
    }

    public String getdeletepart() {
        return "delete from partenaire where intitpart=?";
    }

    public String getAllselect() {
        return "SELECT * FROM partenaire";
    }

    public String getrecherchepart(String txt) {
        return "Select * from partenaire where intitpart like'" + txt + "%'";
    }

}
