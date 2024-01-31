package Day5.Part2;

import java.util.ArrayList;

public class SeedRangeCollection {
    protected record SeedRange(long seedRangeStart, long seedRangeLength) {

    }
    private final ArrayList<SeedRange> seedRanges;

    public SeedRangeCollection() {
        seedRanges = new ArrayList<>();
    }

    public void addSeedRange(long seedRangeStart, long seedRangeLength) {
        SeedRange seedRange = new SeedRange(seedRangeStart, seedRangeLength);
        seedRanges.add(seedRange);
    }

    public ArrayList<SeedRange> getSeedRanges() {
        return seedRanges;
    }

    public String printSeedRanges() {
        return "Seed ranges: " + seedRanges;
    }

    public ArrayList<Long> getAllSeeds() {
        Long seed = null;
        ArrayList<Long> seeds = new ArrayList<>();
        for (SeedRange seedRange : seedRanges) {
            seed = seedRange.seedRangeStart;
            while (seed < (seedRange.seedRangeStart + seedRange.seedRangeLength)) {
                seeds.add(seed);
                seed += 1;
            }
        }
        return seeds;
    }
}
