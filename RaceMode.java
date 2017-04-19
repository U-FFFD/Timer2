interface RaceMode{
  /** race setup methods */
  public void addRacer(int id);
  public void triggerChannel(int ch);
  public void cancel();

  /** racer control methods */
  public void start();
  public void finish();
  public void dnf();
   public void swap();

  /** run control methods */
  public void newRun();
  public void endRun();
  public String print();
  public void export();
}
