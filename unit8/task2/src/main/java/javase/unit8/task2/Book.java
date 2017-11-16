package javase.unit8.task2;

public class Book {
	private final long id;
	private final String name;
	
	public Book(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Book book = (Book) o;
		
		if (id != book.id) return false;
		return name.equals(book.name);
	}
	
	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + name.hashCode();
		return result;
	}
}