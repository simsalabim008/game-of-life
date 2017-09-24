package life;
/**
 * Deze klasse representeert een generatie van cellen
 * op een veld. De afmeting van het veld is vastgelegd door
 * de constanten BREEDTE en HOOGTE.
 * Cellen kunnen groeien naar een volgende generatie
 * volgens de regels van Game of Life van John Conway.
 * De domeinlaag(Life.java) is zelf geschreven naar aanleiding van
 * de cursus Java1 van de Open Universiteit.
 * LifeFrame en LifePanel zijn niet zelfgeschreven.
 */
public class Life {
  
  // constanten  
  public static final int BREEDTE = 40;
  public static final int HOOGTE = 25;

  // attributen  
  private boolean [][] lifeCel = new boolean[HOOGTE][BREEDTE];
 

  /**
   * Geeft de status van de cel op rij i en kolom j.
   * Voorwaarde: 0 <= i < HOOGTE en 0 <= j < BREEDTE
   * @param int i
   * @param int j
   * @return true als bevolkt, anders false
   */
  public boolean isBevolkt(int i, int j) {
      return lifeCel[i][j];
  }

  /**
   * Verandert de status van een cel. Een bevolkte cel
   * wordt onbevolkt en andersom.
   * Voorwaarde: 0 <= i < HOOGTE en 0 <= j < BREEDTE
   */
  public void wisselBevolking(int i, int j) {
    lifeCel[i][j] = !lifeCel[i][j];
  }
  
  /**
   * Kent aan lifecel de volgende generatie cellen toe
   */ 
  public void nieuweGeneratie() {
    boolean[][] nGeneratie = new boolean[HOOGTE][BREEDTE];
    for(int i = 0; i < HOOGTE; i++) {
      for(int j = 0; j < BREEDTE; j++) {
        nGeneratie[i][j] = volgendeCelWaarde(i, j);
      }
    }
    lifeCel = nGeneratie;
  }
  
  /**
   * Bepaalt de volgende cel waarde van een cel in een volgende generatie.
   * @param i cellen[i]
   * @param j cellen[i][j]
   * @return true of false
   */
  private boolean volgendeCelWaarde(int i, int j) {
   int aantal = telBuren(i, j);
   if(lifeCel[i][j] == true && (aantal == 2 || aantal == 3)) { 
    return true;
   } else if (lifeCel[i][j] == false && aantal == 3) { 
     return true;
   }
    return false;
  }

  /**
   * Telt het aantal buren van een cel.
   * Een cel kan maximaal 8 buren hebben.
   * @return int aantal buren.
   */
  private int telBuren(int i, int j) {
    int buren = 0;
    int iMin = Math.max(i - 1, 0);
    int jMin = Math.max(j - 1, 0);
    int iMax = Math.min(i + 1, HOOGTE - 1);
    int jMax = Math.min(j + 1, BREEDTE - 1);
    
    for(int teller = iMin; teller <= iMax; teller++) {
      for(int teller2 = jMin; teller2 <= jMax; teller2++) {
        // De cel zelf wordt overgeslagen
        if(!(teller == i && teller2 == j) && lifeCel[teller][teller2]){
          buren++;
          }
        }
      }
    return buren;
  }
}

