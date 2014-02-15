package main;

public class Timer
{
	private long		_start;
	private long		_lastLap;
	private int			_lapCount;

	public Timer()
	{
		this._start = System.currentTimeMillis();
		this._lastLap = this._start;
		this._lapCount = 0;
	}
	
	public String lap()
	{
		long	snap;
		long	lap;
		long	total;

		snap = System.currentTimeMillis();
		lap = snap - this._lastLap;
		total = snap - this._start;
		this._lastLap = snap;
		this._lapCount++;
		return (String.format("Lap %d: %dms (%dms)",
				this._lapCount, lap, total));
	}
}
