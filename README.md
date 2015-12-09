# Artificial Intelligence classifier

## Usage
Clone this git repository to a directory your desire, with
```
git clone https://github.com/b-vd-b/ailearner.git
```
Import the project into Eclipse or whatever IDE you desire; it should be recognized as a Java project.

In the `model.Definitions.java`, the definitions of the classifier are set, there is an example configuration present in this file.

Your directory structure should be as follows:
```
<git cloning directory>
|_ ailearner
 |_ bin
 |_ src
 |_ TRAINING_DIR             | "training" in example config
 ||_ SET                     | "blogs" in example config
 | |_ CATEGORY_A             | "F" in example config
 | |_ CATEGORY_B             | "M" in example config
 |  |_ <The training files>
 |_ TEST_DIR                 | "test" in example config
  |_ SET                     | "blogs" in example config
   |_ CATEGORY_A             | "F" in example config
   |_ CATEGORY_B             | "M" in example config
    |_ <The files to classify against the training files>
```

In the `test.TestRun.java` file, an example running configuration is found. You can run this and see the outcome of the classifier.

## TODO
This is a list of stuff that needs to be done:
- [x] Build a basic classifier that works with some probability theory and a given training set on Blackboard 
- [ ] Make the classifier suppurt more than two categories
- [ ] Make the classifier interactive so that it learns

