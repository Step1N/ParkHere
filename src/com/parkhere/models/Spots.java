package com.parkhere.models;

import java.util.ArrayList;
import java.util.List;

public class Spots {

	private List<Spot> spots;
	private int size;

	public Spots(int size) {
		spots = new ArrayList<Spot>();
		allocateSpots(size);
	}

	private void allocateSpots(int size) {
		for (int i = 1; i <= size; i++) {
			Spot s = new Spot();
			s.setId("Spot-" + i);
			s.setStatus(SpotStatus.EMPTY);
			s.setIsFree(true);
			s.setDistanceFromFront(i);
			spots.add(s);
		}
	}

	public int getSize() {
		if (size != 0) {
			return size;
		}
		return spots.size();
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Spot> getSpots() {
		return spots;
	}
}
