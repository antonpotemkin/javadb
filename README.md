javadb - simple implementation db from [book](https://www.piter.com/product/vysokonagruzhennye-prilozheniya-programmirovanie-masshtabirovanie-podderzhka).
links 
[leveldb](https://github.com/google/leveldb/blob/main/doc/impl.md)  
[BigTable](https://storage.googleapis.com/pub-tools-public-publication-data/pdf/68a74a85e1662fe02ff3967497f31fda7f32225c.pdf)  


development plan  
1 - ~~simple key value storage~~  
2 - ~~memory cache for storage file with shifted~~
3 - storage byte on the disk
4 - ss tables  
5 - move full log file to ssTable file, create new file, remove old log file
6 - compaction file into 1 file
7 - recovery process from log file to Memtable after crashed app