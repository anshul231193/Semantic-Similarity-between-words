Semantic Similarity/Relatedness Measure between words
The study of semantic similarity between words has been a part of natural language processing and information retrieval for many years. Semantic similarity is a generic issue in a variety of applications in the areas of computational linguistics and artificial intelligence, both in the academic community and industry. Examples include word sense disambiguation, detection and correction of word spelling errors (malapropisms), text segmentation, image retrieval, multimodal documents retrieval, and automatic hypertext linking. Similarity between two words is often represented by similarity between concepts associated with the two words.
To decide whether two words are semantically similar, it is important to know the semantic relations that hold between the words. For example, the words horse and cow can be considered semantically similar because both horses and cows are useful animals in agriculture. Similarly, a horse and a car can be considered semantically similar because cars, and historically horses, are used for transportation.
Accurate extraction of semantically related words requires a precise definition of the closeness between a pair of words, in terms of either the pair wised similarity or distance. A variety of similarity or distance measures have been proposed and widely applied, such as cosine similarity, F-score, Euclidean distance, Mahalanobis distance, and the Jaccard coefficient and Pearson Correlation Coefficient.
METHOD BASED ON PEARSON CORRELATION COEFFICIENT
The Pearson’s Correlation coefficient between ith and jth words is calculated by the following formula:


The steps involved in our proposed method are:
	Step 1: Pre-processing of input data
	Step 2: Semantically related terms extraction


1. Preprocess the given Text Documents (i.e. do stemming, remove stop words).
2. Find list of unique words in all documents.
3. Find frequency of each unique word in all text documents.
4. Remove very high frequent and very low frequent words from the list of unique words.
5. Use remaining list of unique words and generate frequency matrix.
6. Calculate Pearson Correlation Coefficient between each pair of unique words.
7. Find the pair with maximum correlation coefficient.
8. Add this pair of words as a single word at the end of frequency matrix.
9. Eliminate the columns of these two words from the frequency matrix.
10. Repeat Step 6 through 9 till matrix exhaust.

