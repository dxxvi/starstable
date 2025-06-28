# Series

A series is a column in a data frame and has an index. What we can do with a series:
  - see what type pandas gives to our series values (it's not Python types like `int`, `float`, `str`, `list`, `tuple`, `dict`) but numpy types:
      - integers: `np.int8`, `np.int16`, `np.int32` and `np.int64`
      - unsigned integers: `np.uint8`, `np.uint16`, `np.uint32` and `np.uint64`
      - floats: NaN is also a float, e.g `np.float16`, ..., `np.float64`
      - Python object: `object`
  - change index
  - create a slice
  - sort
  - find min, max, average, top/bottom n ... of the values and the indices of those values