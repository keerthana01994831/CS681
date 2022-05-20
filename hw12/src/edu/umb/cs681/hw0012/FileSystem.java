package edu.umb.cs681.hw0012;


public class FileSystem implements Runnable{
	
	private static FileSystem instance;
	public Directory root = null;
	private int tab;

	private FileSystem() {}
	
	public static FileSystem getFileSystem(){
		
		clearSystem();
		
		if (instance == null){
			instance = new FileSystem();
			
		}
		return instance;
	}
	
	private static void clearSystem(){
		// Set singleton instance to null
		// to clear out old instance
		// in case one exists
		instance = null;
	}
	
	public Directory getRootDirectory(){
		if (this.root == null){
				this.root = new Directory(null, "root");
			}
		
		return this.root;
	}

	
	public String getTab(){
		
		String tabs = "";
		String tab = "\t";
		
		for(int i=0; i < this.tab; i++){
			
			tabs = tabs + tab;
		}
		
		return tabs;
		
	}

	
	@Override
	public void run() {
		
		FileSystem fileSystem = FileSystem.getFileSystem();
		
		File rand = new File(fileSystem.getRootDirectory(), "random file", 5);
		
		Directory system = new Directory(fileSystem.root, "system");
		File a = new File(system, "a.jpg", 10);
		File b = new File(system, "b.jpg", 45);
		File c = new File(system, "c.jpg", 6);
		
		
		Directory home = new Directory(fileSystem.root, "home");
		File d = new File(home, "d.jpg", 12);
		
		Directory pictures = new Directory(home, "pictures");
		File e = new File(pictures, "e.jpg", 75);
		File f = new File(pictures, "f.jpg", 33);
		
		Directory newPics = new Directory(pictures, "new pics");
		File g = new File(pictures, "g.jpg", 16 );
		File h = new File(pictures, "h.jpg", 15);
		
		fileSystem.root.appendChild(system);
		fileSystem.root.appendChild(home);
		fileSystem.root.appendChild(rand);
		
		system.appendChild(a);
		system.appendChild(b);
		system.appendChild(c);
		
		pictures.appendChild(e);
		pictures.appendChild(f);
		
		newPics.appendChild(g);
		newPics.appendChild(h);
		
		
		home.appendChild(d);
		home.appendChild(pictures);
		
		pictures.appendChild(newPics);
		
		System.out.print(" " + pictures.getChildren() + "\n");
		System.out.println("Parent directory of fileSystem : " + fileSystem.getRootDirectory());
		System.out.println("Total pictures size : " + pictures.getTotalSize());
	}
	
	public static void main (String args[]) throws InterruptedException{

		for(int i=0;i<2;i++){
			Thread thread = new Thread(new FileSystem());
			System.out.print("Children of Pictures directory" +" : ");
			
		    thread.start();
		    Thread.sleep(5000);
		}
		
		
	}

	

}
