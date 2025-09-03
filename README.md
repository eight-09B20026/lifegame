# Life Game (Conway's Game of Life) — Java/Swing

**Conway のライフゲーム**を Java（Swing）で実装したデスクトップアプリです。盤面サイズを指定して開始し、クリック/ドラッグでセルの生死を切り替えながら、手動ステップや自動進行（Auto）、Undo を使って挙動を確認できます。
個人学習用に作成したため動作保証などは行っておりません。

> モジュール名: `lifegame`（`module-info.java` あり）  
> 主要パッケージ: `lifegame`（`Main`, `BoardModel`, `BoardView`, `Buttons`）

---

## 特徴
- **標準ルール**（生存 2–3 / 誕生 3）を実装
- **Undo** 対応（履歴をスナップショットで保持）
- **Auto** で連続進行（`Timer`/`TimerTask` を利用）
- **クリック/ドラッグ**でセルを反転（描画は `JPanel#paintComponent`）

## デモの考え方
- 起動直後に表示されるダイアログで **列・行** を指定して開始します（スピナー入力）。
- 盤面上をクリック/ドラッグして初期パターンを作成し、**Next** / **Auto** で進行を観察します。

## プロジェクト構成
```
lifegame09B20026/
  └─ lifegame09B20026/
     ├─ src/lifegame/
     │   ├─ Main.java        # 起動/UI 初期化（フレーム、ダイアログ、レイアウト）
     │   ├─ BoardModel.java  # 状態管理・近傍計算・Undo（cells/tmpCells/pastCells）
     │   ├─ BoardView.java   # 描画とマウス操作（クリック/ドラッグで反転）
     │   └─ Buttons.java     # ボタン/リスナー（next/undo/auto/new game）
     ├─ bin/                 # （Eclipse 実行時のビルド成果物）
     ├─ module-info.java     # module lifegame { requires java.desktop; }
     ├─ .project / .classpath / .settings/（Eclipse 設定）
     └─ README.md            # 本ファイル
```

## 動作要件
- **Java 11+**（`java.desktop` を利用）
- OS: Windows / macOS / Linux
- （任意）Eclipse もしくは IntelliJ IDEA などの Java IDE

## ビルドと実行

### Eclipse/IDE
1. Eclipse で **Existing Projects into Workspace** を選択し、プロジェクトフォルダをインポート。  
2. `lifegame.Main` を **Run**。起動ダイアログで盤面サイズ（列・行）を設定してスタート。

### コマンドライン（モジュール）
`module-info.java` を含むため、**モジュールモード**でのビルド/実行例を示します。

```bash
# 1) コンパイル（出力先: out）
find ./src -name "*.java" > sources.txt
javac @sources.txt -d out

# 2) 実行（モジュール lifegame / メイン: lifegame.Main）
java --module-path out -m lifegame/lifegame.Main
```

> OS によっては `find` コマンドの代わりに PowerShell 等でソースリストを作成してください。  
> 既存の `bin/` は Eclipse の成果物です。クリーンビルドを推奨します。

## 使い方
- **クリック/ドラッグ**：セルの生死をトグル
- **Next**：1 世代進める
- **Auto**：自動進行の開始/停止（トグル）
- **Undo**：直前の状態に戻す（履歴がなければ無効）
- **New Game**：新規に盤面サイズを設定し直して開始

## 謝辞
Conway の Game of Life および Java/Swing のコミュニティに感謝します。
