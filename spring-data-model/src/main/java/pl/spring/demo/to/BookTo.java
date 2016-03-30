package pl.spring.demo.to;

import pl.spring.demo.enumerations.BookStatus;

public class BookTo {
    private Long id;
    private String title;
    private String authors;
    private BookStatus status;
    
    public BookTo() {
    }

    public BookTo(Long id, String title, String authors, BookStatus status) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.setStatus(status);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

	public BookStatus getStatus() {
		return status;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}
	
	@Override
	public boolean equals(Object other) {
		BookTo a = (BookTo) other;
		if(a.id != id)
			return false;
		if(a.title != title)
			return false;
		if(a.authors != authors)
			return false;
		return true;
			
	}
	
	@Override
	public int hashCode() {
		return (int) (id*100+title.hashCode()*10+authors.hashCode());
	}
}
