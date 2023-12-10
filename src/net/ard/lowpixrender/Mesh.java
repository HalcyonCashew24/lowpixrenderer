package net.ard.lowpixrender;

import java.awt.Color;
import java.awt.Graphics;

import net.ard.lowpixrender.Vec3.Gimble;

public class Mesh {
	
	static final Vec3 light = new  Vec3(-1.0f, -1.0f, 1.0f).normalize(1.0f);

	Vec3[] points;
	int[] indices;
	
	Vec3 pos;
	Vec3 scale;
	Vec3 rot;
	Vec3 color;
	
	Mesh(Vec3[] points, int[] indices) {
		try {
			if (indices.length % 3 != 0)
				throw new Exception("Error: created mesh without triangular indices (indices.length % 3 != 0)");
				
			this.points = points;
			this.indices = indices;
			
			pos = new Vec3();
			scale = new Vec3(1.0f, 1.0f, 1.0f);
			rot = new Vec3();
			color = new Vec3(1.0f, 1.0f, 1.0f);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g, Player p) {
		try {
			if (indices == null || points == null)
				throw new Exception("Error: attepted to draw null mesh (indices == null || points == null)");
		
			for (int i = 0; i < indices.length; i += 3) {
				Vec3 p1 = points[indices[i]].clone();
				Vec3 p2 = points[indices[i+1]].clone();
				Vec3 p3 = points[indices[i+2]].clone();
				
				p1.scale(scale).rotate(rot).translate(pos);
				p2.scale(scale).rotate(rot).translate(pos);
				p3.scale(scale).rotate(rot).translate(pos);
				
				p1.translate(new Vec3(-p.pos.x, -p.pos.y, -p.pos.z)).rotate(p.rot, Gimble.YXZ);
				p2.translate(new Vec3(-p.pos.x, -p.pos.y, -p.pos.z)).rotate(p.rot, Gimble.YXZ);
				p3.translate(new Vec3(-p.pos.x, -p.pos.y, -p.pos.z)).rotate(p.rot, Gimble.YXZ);
				
				//Vec3 l = light.rotated(p.rot.x, p.rot.y, p.rot.z);
				Vec3 l = new Vec3(0, 0, 1);
				
				Vec3 n = p2.translated(-p1.x, -p1.y, -p1.z).cross(p3.translated(-p1.x, -p1.y, -p1.z)).normalize(1.0f);
			
				if (n.dot(p1) < 0) {
				
					float c = -n.dot(l);
					
					p1.project().screen();
					p2.project().screen();
					p3.project().screen();
					
					if (p1.z > 0 && p1.z <= 1 &&
						p2.z > 0 && p2.z <= 1 &&
						p3.z > 0 && p3.z <= 1) {
						
						if (c < 0)
							c = 0;
						c += 0.1f;
						c /= 1.1f;
						
						g.setColor(new Color(c * color.x, c * color.y, c * color.z));
						
						g.fillPolygon(new int[] {
							(int) p1.x,
							(int) p2.x,
							(int) p3.x
						}, new int[] {
							(int) p1.y,
							(int) p2.y,
							(int) p3.y
						}, 3);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
