## DVD Rental Project README

### Introduction
このプロジェクトは、システム開発演習Bの一環として、DVDレンタルシステムを開発するものです。
デフォルトの状態では、DummyRepositoryを使用しているため、データの永続化や共有はされません。
また、このプロジェクトではAPIを使用しているため、APIの仕様に従ってリクエストを送信する必要があります。(その実装は`infrastructure/gateway`モジュールに実装されています。
)

### Repository
https://github.com/urassh/dvd-rental

### How to run (Dummy)
そのまま`RentalApp.java`を実行してください。

### 検証用のDummyデータ
貸し出し処理でIDが求められるのでこれらのメンバーIDを使ってください。
- メンバー
  - ID: `123e4567-e89b-12d3-a456-426614174000`, 名前: Alice
  - ID: `987f6543-b21a-34c5-d678-567812345678`, 名前: Bob
  - ID: `456e1234-a78b-90c1-b234-1234567890ab`, 名前: Charlie

### How to run (Production)
1. `.env.sample`をコピーして`.env`を作成し、環境変数を設定してください。(三川先生に提出するプロジェクトは、`.env`ファイルが提供されています。)
2. `module`ディレクトリの`GoodsModule.java`と`MemberModule.java`で、DI(依存性の注入)が行われています。以下のように変更すると必要なデータをAPIサーバにリクエストすることができます。

---

#### Before
GoodsModule.java
```java
// Repository
bind(GoodsRepository.class).to(GoodsDummyRepository.class).in(Singleton.class);
```

MemberModule.java
```java
// Repository
bind(MemberRepository.class).to(MemberDummyRepository.class).in(Singleton.class);
```

---

#### After
GoodsModule.java
```java
// Repository
bind(GoodsRepository.class).to(GoodsRepositoryImpl.class).in(Singleton.class);
```

MemberModule.java
```java
// Repository
bind(MemberRepository.class).to(MemberRepositoryImpl.class).in(Singleton.class);
```

---

### 検証用のDummy