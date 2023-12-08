package net.ard.lowpixrender;

public class Point {
	
	static final float NEAR = 0.1f;
	static final float FAR = 100.0f;
	static final float FOV = 1.0f;
	
	static float a;
	static float f = 1.0f / (float) Math.tan(FOV / 2.0f);
	static float q = FAR / (FAR - NEAR);
	static float width;
	static float height;
	
	public static void setAspect(int width, int height) {
		a = (float) height / (float) width;
		Point.width = width;
		Point.height = height;
	}

	float x;
	float y;
	float z;
	
	Point(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	Point() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Point transformedPoint() {
		float w = z;
		
		Point newPoint = new Point();
		newPoint.x = x * f * a;
		newPoint.y = y * f;
		newPoint.z = z * q - NEAR * q;
		
		if (w != 0) {
			newPoint.x /= w;
			newPoint.y /= w;
			newPoint.z /= w;
		}
		
		newPoint.x *= width / 2.0f;
		newPoint.y *= -height / 2.0f;
		
		newPoint.x += (float) width / 2.0f;
		newPoint.y += (float) height / 2.0f;
		
		return newPoint;
	}
}
