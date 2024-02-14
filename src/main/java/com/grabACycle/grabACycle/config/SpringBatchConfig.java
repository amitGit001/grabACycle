package com.grabACycle.grabACycle.config;

import com.grabACycle.grabACycle.dao.CycleRepository;
import com.grabACycle.grabACycle.entity.Cycle;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@AllArgsConstructor
public class SpringBatchConfig {

    @Autowired
    private CycleRepository cycleRepository;

    @Bean
    public FlatFileItemReader<Cycle> reader()
    {
        FlatFileItemReader<Cycle> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/cycle_data.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());

        return itemReader;
    }

    private LineMapper<Cycle> lineMapper()
    {
        DefaultLineMapper<Cycle> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id","booking_status","model","name","type");

        BeanWrapperFieldSetMapper<Cycle> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Cycle.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }

    @Bean
    public CycleProcessor processor()
    {
        return new CycleProcessor();
    }

    @Bean
    public RepositoryItemWriter<Cycle> writer()
    {
        RepositoryItemWriter<Cycle> writer = new RepositoryItemWriter<>();
        writer.setRepository(cycleRepository);
        writer.setMethodName("save");

        return writer;
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("csv-step",jobRepository).
                <Cycle, Cycle>chunk(10,transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job runJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("importCycles",jobRepository)
                .flow(step1(jobRepository,transactionManager)).end().build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }
}
