package cmsc420_s23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SMkdTree<LPoint extends LabeledPoint2D> {
	
	private abstract class Node {
		abstract LPoint find(Point2D q);
		abstract Node insert(LPoint pt, Rectangle2D cell) throws Exception;
		abstract Node delete(Point2D pt) throws Exception;
		abstract void traverse(List<LPoint> points);
	}
	
	private class InternalNode extends Node {
		LPoint find(Point2D q) {
			if (q == null) {
				return null;
			}
			if (cutDim == 0) {
				if (q.getX() < cutVal) {
					return left.find(q);
				} else {
					return right.find(q);
				}
			} else {
				if (q.getY() < cutVal) {
					return left.find(q);
				} else {
					return right.find(q);
				}
			}
		}		
		Node insert(LPoint pt, Rectangle2D cell) throws Exception {
			List<LPoint> points = new ArrayList<LPoint>();
			if (cutDim == 0) {
				if (pt.getX() < cutVal) {
					left = left.insert(pt, cell.leftPart(cutDim, cutVal));
					size++;
					insertCt++;
					traverse(points);
					rebuild(points, cell);
					return this;
				} else {
					right = right.insert(pt, cell.rightPart(cutDim, cutVal));
					size++;
					insertCt++;
					traverse(points);
					rebuild(points, cell);
					return this;
				}
			} else {
				if (pt.getY() < cutVal) {
					left = left.insert(pt, cell.leftPart(cutDim, cutVal));
					size++;
					insertCt++;
					traverse(points);
					rebuild(points, cell);
					return this;
				} else {
					right = right.insert(pt, cell.rightPart(cutDim, cutVal));
					size++;
					insertCt++;
					traverse(points);
					rebuild(points, cell);
					return this;
				}
			}
		}
		
		void traverse(List<LPoint> points) {
			left.traverse(points);
			right.traverse(points);
		}
		void rebuild(List<LPoint> points, Rectangle2D cell) {
			if (insertCt > (size + rebuildOffset)/2) {
				Node node = bulkCreate(points, cell);
				InternalNode internalNode = (InternalNode) node;
				this.left = internalNode.left;
				this.right = internalNode.right;
				this.cutDim = internalNode.cutDim;
				this.cutVal = internalNode.cutVal;
				this.size = internalNode.size;
				this.insertCt = 0;
			}
		}
		
		Node delete(Point2D pt) throws Exception {
			if (pt == null) {
				return null;
			}
			if (cutDim == 0) {
				if (pt.getX() < cutVal) {
					left = left.delete(pt);
				} else {
					right = right.delete(pt);
				}
			} else {
				if (pt.getY() < cutVal) {
					left = left.delete(pt);
				} else {
					right = right.delete(pt);
				}
			}
			size--;
			return this;
		}
		int cutDim;
		double cutVal;
		Node left, right;
		int size = sizeHelper(this);
		int insertCt;
	}
	
	private class ExternalNode extends Node {
		LPoint find(Point2D q) {
			if (q == null) {
				return null;
			}
			if (point != null && point.getPoint2D().equals(q)) {
				return point; 
			}
			return null;
		}		
		Node insert(LPoint pt, Rectangle2D cell) throws Exception {
			if (point == null) {
				point = pt;
			} else if (point.getPoint2D().equals(pt.getPoint2D())) {
				throw new Exception("Insertion of duplicate point");
			} else {
				List<LPoint> points = new ArrayList<LPoint>();
				points.add(point);
				points.add(pt);
				Node node = bulkCreate(points, cell);
				if (root instanceof SMkdTree.ExternalNode) {
					root = node;
				}
				return node;
			}
			return this;
		}
		void traverse(List<LPoint> points){
			if (point != null) {
				points.add(point);
			}
		}
		
		Node delete(Point2D pt) throws Exception {
			if (this.point == null) {
				throw new Exception("Deletion of nonexistent point");
			}
			if (!point.getPoint2D().equals(pt)) {
				throw new Exception("Deletion of nonexistent point");
			}
			if (point.getPoint2D().equals(pt)) {
				deleteCt++;
				this.point = null; 
				return this;
			}
			return this;
		}
		LPoint point;
	}
	
	private class ByXThenY implements Comparator<LPoint> {
		public int compare(LPoint pt1, LPoint pt2) {
			if (pt1.getX() < pt2.getX()) {
	            return -1;
	        } else if (pt1.getX() > pt2.getX()) {
	            return 1;
	        } else {
	            if (pt1.getY() < pt2.getY()) {
	                return -1;
	            } else if (pt1.getY() > pt2.getY()) {
	                return 1;
	            } else {
	                return 0;
	            }
	        }
		}
	}
	private class ByYThenX implements Comparator<LPoint> {
		public int compare(LPoint pt1, LPoint pt2) {
			if (pt1.getY() < pt2.getY()) {
	            return -1;
	        } else if (pt1.getY() > pt2.getY()) {
	            return 1;
	        } else {
	            if (pt1.getX() < pt2.getX()) {
	                return -1;
	            } else if (pt1.getX() > pt2.getX()) {
	                return 1;
	            } else {
	                return 0;
	            }
	        }
		}
	}
	
	private int size;
    private int rebuildOffset;
    private Rectangle2D rootCell;
    private Node root;
    private int deleteCt;
	
	public SMkdTree(int rebuildOffset, Rectangle2D rootCell) { 
		this.rebuildOffset = rebuildOffset;
		this.rootCell = rootCell;
		this.size = 1;
		ExternalNode node = new ExternalNode();
		node.point = null;
		this.root = node;
	}
	
	public void clear() { 
		this.size = 0;
		ExternalNode node = new ExternalNode();
		node.point = null;
		this.root = node;
	}
	
	public int size() { 
		return sizeHelper(this.root);
	}
	
	private int sizeHelper(Node node) {
		if (node instanceof SMkdTree.InternalNode) {
			return sizeHelper(((SMkdTree.InternalNode) node).left) + sizeHelper(((SMkdTree.InternalNode) node).right);
		} else {
			ExternalNode eNode = (ExternalNode) node;
			if (eNode == null || eNode.point == null) {
				return 0;
			}
			return 1;
		}
	}
	
	public int deleteCount() { 
		return this.deleteCt;
	}
	
	public LPoint find(Point2D q) {
	    if (this.root instanceof SMkdTree.ExternalNode && ((SMkdTree.ExternalNode) this.root).point == null) {
	    	return null;
	    }
		return this.root.find(q);
	}

	
	
	public void insert(LPoint pt) throws Exception {
		if (!rootCell.contains(pt.getPoint2D())) {
			throw new Exception("Attempt to insert a point outside the bounding box");
		}
		this.root.insert(pt, rootCell);
	}
	
	public void delete(Point2D pt) throws Exception { 
		if (!rootCell.contains(pt)) {
			throw new Exception("Deletion of nonexistent point");
		}
		this.root.delete(pt);
		if (deleteCount() > sizeHelper(root)) {
			List<LPoint> points = new ArrayList<LPoint>();
			root.traverse(points);
			Node node = bulkCreate(points, rootCell);
			this.root = node;
			if (this.root instanceof SMkdTree.InternalNode) {
				((SMkdTree.InternalNode) this.root).insertCt = 0;
			}
			this.deleteCt = 0;
		}
	}
	
	private void listHelper(Node node, ArrayList<String> output) {
	    if (node == null) {
	        return;
	    }
	    
	    if (node instanceof SMkdTree.InternalNode) {
	        InternalNode internalNode = (InternalNode) node;
	        String dim = (internalNode.cutDim == 0) ? "x" : "y";
	        String str = "(" + dim + "=" + internalNode.cutVal + ") " + internalNode.size + ":" + internalNode.insertCt;
	        output.add(str);
	    } else if (node instanceof SMkdTree.ExternalNode) {
	        ExternalNode externalNode = (ExternalNode) node;
	        String str = (externalNode.point == null) ? "[null]" : "[" + externalNode.point.toString() + "]";
	        output.add(str);
	    }
	    
	    if (node instanceof SMkdTree.InternalNode) {
	    	InternalNode internalNode = (InternalNode) node;
	    	listHelper(internalNode.right, output);
	    	listHelper(internalNode.left, output);
	    }
	    
	}
	
	public ArrayList<String> list() { 
		ArrayList<String> output = new ArrayList<>();
	    listHelper(root, output);
	    return output;
	}
	
	public LPoint nearestNeighbor(Point2D center) { 
		if (this.root == null) {
			return null; 
		} 
		List<LPoint> list = new ArrayList<LPoint>();
		return nearestNeighbor(center, root, rootCell, null, list);
	}
	
	public LPoint nearestNeighbor(Point2D center, Node node, Rectangle2D cell, LPoint best, List<LPoint> list) {
		if (node == null) return best;
		if (node instanceof SMkdTree.ExternalNode) {
			ExternalNode externalNode = (ExternalNode) node;
			if (externalNode.point != null) list.add(externalNode.point);
			
			if (best == null || (externalNode.point != null && center.distanceSq(((SMkdTree.ExternalNode) node).point.getPoint2D()) <= center.distanceSq(best.getPoint2D()))){
				if (best == null) {
					best = externalNode.point;
				} else if (best != null && center.distanceSq(externalNode.point.getPoint2D()) == center.distanceSq(best.getPoint2D())) {
					if (externalNode.point.getX() < best.getX()) {
						best = externalNode.point;
					} else if (externalNode.point.getX() == best.getX()) {
						if ((externalNode.point.getY() < best.getY())) {
							best = externalNode.point;
						}
					}
				} else if (best != null && center.distanceSq(externalNode.point.getPoint2D()) < center.distanceSq(best.getPoint2D())) {
					best = externalNode.point;
				}
			}
		}
		if (node instanceof SMkdTree.InternalNode) {
			InternalNode internalNode = (InternalNode) node;
			Rectangle2D leftPart = cell.leftPart(internalNode.cutDim, internalNode.cutVal);
			Rectangle2D rightPart = cell.rightPart(internalNode.cutDim, internalNode.cutVal);
			if (center.get(internalNode.cutDim) < internalNode.cutVal) {
				best = nearestNeighbor(center, internalNode.left, leftPart, best, list);
				if (best == null) {
					best = nearestNeighbor(center, internalNode.right, rightPart, best, list);
				} else if (rightPart.distanceSq(center) <= center.distanceSq(best.getPoint2D())) {
					best = nearestNeighbor(center, internalNode.right, rightPart, best, list);
				}
			} else {
				best = nearestNeighbor(center, internalNode.right, rightPart, best, list);
				if (best == null) {
					best = nearestNeighbor(center, internalNode.left, leftPart, best, list);
				} else if (leftPart.distanceSq(center) <= center.distanceSq(best.getPoint2D())) {
					best = nearestNeighbor(center, internalNode.left, leftPart, best, list);
				}
			}
		}
		return best;
	}
	
	
	
	
	
	public ArrayList<LPoint> nearestNeighborVisit(Point2D center) { 
		if (root instanceof SMkdTree.ExternalNode) {
			return new ArrayList<LPoint>(); 
		} 
		ArrayList<LPoint> list = new ArrayList<LPoint>();
		nearestNeighbor(center, root, rootCell, null, list);	
		Collections.sort(list, new ByXThenY());
		return list;
	}
	
	private class LPointIterator implements Iterator<LPoint> {
        public LPointIterator() { /* ... */ }
        public boolean hasNext() { /* ... */ return false; }
        public LPoint next() throws NoSuchElementException { /* ... */ return null; }

    }
    public LPointIterator iterator() { /* ... */ return new LPointIterator(); }
	
    Node bulkCreate(List<LPoint> pts, Rectangle2D cell) {
		if (pts.size() == 0) { 
			return new ExternalNode(); 
		} else if (pts.size() == 1) { 
			ExternalNode node = new ExternalNode();
			node.point = pts.get(0);
			return node; 
		} else { 
			int cutDim;
			if((cell.getHigh().getY() - cell.getLow().getY()) > cell.getHigh().getX() - cell.getLow().getX()) {
				Collections.sort(pts, new ByYThenX());
				cutDim = (pts.get(0).getY() == (pts.get(pts.size() - 1).getY())) ? 0 : 1;
			} else {
				Collections.sort(pts, new ByXThenY());
				cutDim = (pts.get(0).getX() == (pts.get(pts.size() - 1).getX())) ? 1 : 0;
			}
			InternalNode node = new InternalNode();
			double cutVal;
			if(cutDim == 0) {
				cutVal = ((cell.getHigh().getX() - cell.getLow().getX()) / 2) + cell.getLow().getX();
				int midPoint = pts.size();
				if(cutVal < pts.get(0).getX()) {
					cutVal = pts.get(0).getX();
				} else if (cutVal > pts.get(pts.size() - 1).getX()) {
					cutVal = pts.get(pts.size()-1).getX();
				}
				int i = 0;
				for (LPoint point : pts) {
					if (cutVal <= point.getX()) {
						midPoint = i;
						break;
					}
					i++;
				}
				node.cutDim = 0;
				node.cutVal = cutVal;
				node.size = pts.size();
				node.left = bulkCreate(pts.subList(0, midPoint), cell.leftPart(cutDim, cutVal));
				node.right = bulkCreate(pts.subList(midPoint, pts.size()), cell.rightPart(cutDim, cutVal));
				return node;
			} else {
				cutVal = ((cell.getHigh().getY() - cell.getLow().getY()) / 2) + cell.getLow().getY();
				int midPoint = pts.size();
				if (cutVal < pts.get(0).getY()) {
					cutVal = pts.get(0).getY();
				} else if (cutVal > pts.get(pts.size() - 1).getY()) {
					cutVal = pts.get(pts.size() - 1).getY();
				}
				int i = 0;
				for (LPoint point : pts) {
					if (cutVal <= point.getY()) {
						midPoint = i;
						break;
					}
					i++;
				}
				node.cutDim = 1;
				node.cutVal = cutVal;
				node.size = pts.size();
				node.left = bulkCreate(pts.subList(0, midPoint), cell.leftPart(cutDim, cutVal));
				node.right = bulkCreate(pts.subList(midPoint, pts.size()), cell.rightPart(cutDim, cutVal));
				return node;
			}
		}
	}
}
