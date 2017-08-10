# gochariots-java-lib
Java library for GoChariots

## Quickstart
    long seed = 9527;

    Record record1 = new Record(seed);
    record1.add("first", "event"); // add record's content as key-value pairs
    long hash1 = Gochariots.getHash(record1).get(0);

    Record record2 = new Record(seed);
    record2.add("second", "event");
    record2.addHash(hash1); // set the second event's prerequisite hash

    Gochariots g = new Gochariots("localhost:8080");
    // post records
    g.post(record2);
    g.post(record1);
