# TugasBesar-OOPSTI
Tugas Besar - IF2212 OOP STI - Kelompok 04 - K02

Anggota kelompok :
1. Irfan Mustofa / 18222056
2. Eldwin Pradipta / 18222042
3. Farras Isfahan Akbar / 18219020
4. Ammar Naufal / 18222066
5. Theo Livius Natael / 18222076

## Michael VS Lalapan
Michael vs Lalapan adalah permainan dimana player harus mencegah zombie sampai ke dalam rumah player dengan menanam tanaman yang dapat membunuh dan menghalangi zombie sebelum mereka sampai masuk ke dalam rumah. Map dalam game berupa grid 6x9 dimana zombie akan muncul dari bagian paling kanan dan terus berjalan ke kiri. 

Player akan menang jika player berhasil bertahan melewati malam hari. Player akan kalah jika zombie berhasil sampai ke ujung kiri map dan masuk ke dalam rumah player. 

### Cara memulai permainan
- Buka folder TUGASBESAR-OOPSTI 
- Ketik "./run.bat atau run.bat"(untuk Windows) atau ketik "./run.sh" (untuk Mac) di terminal


## Alur permainan
### Main menu

Pada main menu player akan diberikan 5 opsi input. Untuk memulai permainan, player memilih opsi start game dengan menginput "1" di command line

![alttext](/Screenshot%20(1597).png)

### Membuat deck

Pada pembuatan deck player akan mengisi deck dengan 6 tanaman untuk digunakan dalam permainan. Terminal akan memunculkan list tanaman yang dapat dimasukkan ke dalam deck.Player dapat melakukan 3 hal pada bagian ini, yaitu :

1. Menambah tanaman <br>
Dengan menginput 1 x (x adalah tanamannya). Player dapat menambahkan tanaman ke dalam deck

![alttext](/Screenshot%20(1599).png)

2. Menghapus tanaman dari deck<br>
Jika tidak jadi menggunakan tanaman yang sudah ditambah ke deck. Player dapat menghapus tanaman tersebut dari deck dengan menginput 2 x (x adalah tanaman yang ingin dihapus)

![alttext](/Screenshot%20(1600).png)

3. Menukar posisi tanaman<br>
Jika player ingin menukar posisi tanaman dalam deck, player dapat menginput 3 x y (x dan y adalah tanaman yang posisinya ditukar)

![alttext](/Screenshot%20(1601).png)

### Permainan
Dalam permainan player dapat melakukan beberapa hal, yaitu :

1. Print map<br>
Dengan menginput satu player dapat menampilkan status permainan dengan mengprint map ke terminal
![](/Screenshot%20(1603).png)

*P adalah protected area yang jika zombie sampai ke titik itu, maka player akan kalah <br>
[L] adalah tile yang berupa daratan <br>
[W] adalah tile yang berupa air
*S adalah spawn area zombie <br>
Zombie ditunjukkan dengan lambang Z berupa list <br>
Jika sudah menanam plant, maka plant akan ditunjukkan dengan P.X (x adalah plant tersebut berdasarkan posisi deck)

2. Menanam plant<br>
Player dapat menanam plant dengan mengurangi jumlah matahari yang dimiliki sebanyak harga plant tersebut. <br>
Plant ada yang bisa ditanam di darat(semua plant selain kelp dan lily pad) dan ada yang hanya bisa ditanam di air(lily pad dan kelp)<br>
<br>
Untuk menanam plant di tile air maka player harus menanam lily pad terlebih dahulu di tile air sebelum menanam tanaman darat
<br>
![](/Screenshot%20(1608).png)
Input menanam plant adalah 2 x y z dimana x adalah plant dari posisi deck, y adalah row, dan z adalah kolom. <br>
Row yang bisa dipilih dimulai dari 1-6 dan kolom yang dipilih adalah 1-9

<br>
3. Menghilangkan plant dari map<br>
Untuk menghilangkan plant dari map, player bisa melakukan input 3 x y (x adalah row dan y adalah kolom) <br>

![](/Screenshot%20(1603).png)

<BR>
<br>
Permainan akan berlanjut hingga player kalah atau menang
