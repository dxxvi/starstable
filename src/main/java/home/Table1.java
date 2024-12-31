package home;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.function.Supplier;

@Entity
@Table(name = "TABLE1")
public class Table1 {

  @EmbeddedId private Table1Id id;

  @Column(name = "OPEN", nullable = false)
  private Float open;

  @Column(name = "HIGH", nullable = false)
  private Float high;

  @Column(name = "LOW", nullable = false)
  private Float low;

  @Column(name = "CLOSE", nullable = false)
  private Float close;

  @Column(name = "MA1")
  private Float ma1;

  @Column(name = "MA2")
  private Float ma2;

  @Column(name = "MA3")
  private Float ma3;

  @Column(name = "MA4")
  private Float ma4;

  @Column(name = "MA5")
  private Float ma5;

  @Column(name = "VOLUME")
  private Integer volume;

  @Column(name = "BASIS")
  private Float basis;

  @Column(name = "UPPER")
  private Float upper;

  @Column(name = "LOWER")
  private Float lower;

  @Column(name = "HISTOGRAM")
  private Float histogram;

  @Column(name = "MACD")
  private Float macd;

  @Column(name = "SIGNAL")
  private Float signal;

  @Column(name = "PERCENTK")
  private Float percentk;

  @Column(name = "PERCENTD")
  private Float percentd;

  @Column(name = "RSI")
  private Float rsi;

  @Column(name = "RSIBASEDMA")
  private Float rsiBasedMa;

  public Table1Id getId() {
    return id;
  }

  public Table1 setId(Table1Id id) {
    this.id = id;
    return this;
  }

  public Float getOpen() {
    return open;
  }

  public Table1 setOpen(Float open) {
    this.open = open;
    return this;
  }

  public Float getHigh() {
    return high;
  }

  public Table1 setHigh(Float high) {
    this.high = high;
    return this;
  }

  public Float getLow() {
    return low;
  }

  public Table1 setLow(Float low) {
    this.low = low;
    return this;
  }

  public Float getClose() {
    return close;
  }

  public Table1 setClose(Float close) {
    this.close = close;
    return this;
  }

  public Float getMa1() {
    return ma1;
  }

  public Table1 setMa1(Float ma1) {
    this.ma1 = ma1;
    return this;
  }

  public Float getMa2() {
    return ma2;
  }

  public Table1 setMa2(Float ma2) {
    this.ma2 = ma2;
    return this;
  }

  public Float getMa3() {
    return ma3;
  }

  public Table1 setMa3(Float ma3) {
    this.ma3 = ma3;
    return this;
  }

  public Float getMa4() {
    return ma4;
  }

  public Table1 setMa4(Float ma4) {
    this.ma4 = ma4;
    return this;
  }

  public Float getMa5() {
    return ma5;
  }

  public Table1 setMa5(Float ma5) {
    this.ma5 = ma5;
    return this;
  }

  public Integer getVolume() {
    return volume;
  }

  public Table1 setVolume(Integer volume) {
    this.volume = volume;
    return this;
  }

  public Table1 setVolume(Supplier<Integer> volumeSupplier) {
    this.volume = volumeSupplier.get();
    return this;
  }

  public Float getBasis() {
    return basis;
  }

  public Table1 setBasis(Float basis) {
    this.basis = basis;
    return this;
  }

  public Float getUpper() {
    return upper;
  }

  public Table1 setUpper(Float upper) {
    this.upper = upper;
    return this;
  }

  public Float getLower() {
    return lower;
  }

  public Table1 setLower(Float lower) {
    this.lower = lower;
    return this;
  }

  public Float getHistogram() {
    return histogram;
  }

  public Table1 setHistogram(Float histogram) {
    this.histogram = histogram;
    return this;
  }

  public Float getMacd() {
    return macd;
  }

  public Table1 setMacd(Float macd) {
    this.macd = macd;
    return this;
  }

  public Float getSignal() {
    return signal;
  }

  public Table1 setSignal(Float signal) {
    this.signal = signal;
    return this;
  }

  public Float getPercentk() {
    return percentk;
  }

  public Table1 setPercentk(Float percentk) {
    this.percentk = percentk;
    return this;
  }

  public Float getPercentd() {
    return percentd;
  }

  public Table1 setPercentd(Float percentd) {
    this.percentd = percentd;
    return this;
  }

  public Float getRsi() {
    return rsi;
  }

  public Table1 setRsi(Float rsi) {
    this.rsi = rsi;
    return this;
  }

  public Float getRsiBasedMa() {
    return rsiBasedMa;
  }

  public Table1 setRsiBasedMa(Float rsibasedma) {
    this.rsiBasedMa = rsibasedma;
    return this;
  }
}
