package home;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import org.hibernate.Hibernate;

@Embeddable
public class Table1Id {
  @Column(name = "SYMBOL", nullable = false, length = 7)
  private String symbol;

  @Column(name = "INTV", nullable = false)
  private int intv;

  @Column(name = "TIM3", nullable = false)
  private long tim3;

  public Table1Id() {}

  public Table1Id(String symbol, int intv, long tim3) {
    this.symbol = symbol;
    this.intv = intv;
    this.tim3 = tim3;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public int getIntv() {
    return intv;
  }

  public void setIntv(int intv) {
    this.intv = intv;
  }

  public long getTim3() {
    return tim3;
  }

  public void setTim3(long tim3) {
    this.tim3 = tim3;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Table1Id entity = (Table1Id) o;
    return Objects.equals(this.symbol, entity.symbol)
        && Objects.equals(this.intv, entity.intv)
        && Objects.equals(this.tim3, entity.tim3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(symbol, intv, tim3);
  }

  @Override
  public String toString() {
    return "(" + symbol + ", intv=" + intv + "m, tim3=" + tim3 + ')';
  }
}
