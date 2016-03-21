package njit.cs602.qiyi.assignment2.group.payroll;

/**
 * <p>
 * PieceWorker
 * </p>
 *
 * @author qiyi
 * @version 2016-3-20
 */
public class PieceWorker extends Employee {
    private double wage; // wage per piece
    private double pieces; // number of pieces produced

    public PieceWorker(String firstName, String lastName,
            String socialSecurityNumber, double wage, double pieces) {
        super(firstName, lastName, socialSecurityNumber);
        // TODO Auto-generated constructor stub
        
        if (wage < 0.0) // validate wage
            throw new IllegalArgumentException(
               "wage per piece must be >= 0.0");
        this.wage = wage;
        this.pieces = pieces;
    }
    
    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        if (wage < 0.0) // validate wage
            throw new IllegalArgumentException(
               "wage per piece must be >= 0.0");
        this.wage = wage;
    }

    public double getPieces() {
        return pieces;
    }

    public void setPieces(double pieces) {
        this.pieces = pieces;
    }


    @Override
    public double earnings() {
        // TODO Auto-generated method stub
        return wage * pieces;
    }
    @Override
    public String toString(){
        return String.format("piece worker: %s%n%s: $%,.2f; %s: %,.2f",
                super.toString(), "wage per piece", getWage(),                     
                "pieces produced", getPieces());         
    }
}
