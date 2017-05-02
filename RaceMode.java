interface RaceMode{
  /** race setup methods */
  void addRacer(int id);
  void triggerChannel(int ch);
  void cancel();

  /** racer control methods */
  void start();
  void finish();
  void dnf();

  /** run control methods */
  void newRun();
  void endRun();
  String print();
  void export();
  String format();
}
