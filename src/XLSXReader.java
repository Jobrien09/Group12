package test;

import java.util.List;
public interface XLSXReader<T, S> extends iReader{
	public List<T> read(List<S> list);
}
